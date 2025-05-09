package com.example.bootcampfinalproject.data.repository

import com.example.bootcampfinalproject.domain.repository.AuthRepository
import com.example.bootcampfinalproject.util.ResponseState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.example.bootcampfinalproject.R

class FirebaseAuthImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository{

    override suspend fun registerEmailAndPassword(
        email: String,
        password: String
    ): ResponseState<FirebaseUser> {
        return try {
           val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null){
                ResponseState.Success(user)
            } else {
                ResponseState.Error(R.string.unknown_error.toString())
            }
        } catch (e: Exception) {
            ResponseState.Error(e.localizedMessage ?: R.string.an_error_occurred_during_the_login_process.toString())
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): ResponseState<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                ResponseState.Success(user)
            } else {
                ResponseState.Error(R.string.user_information_could_not_be_retrieved.toString())
            }
        } catch (e: Exception) {
            ResponseState.Error(e.localizedMessage ?: R.string.login_failed.toString())
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): Flow<ResponseState<FirebaseUser?>> {
        return flow{
            emit(ResponseState.Loading)
            val user = firebaseAuth.currentUser
            if (user != null) {
                emit(ResponseState.Success(user))
            } else {
                emit(ResponseState.Error(R.string.user_not_found.toString()))
            }
        }
    }

}