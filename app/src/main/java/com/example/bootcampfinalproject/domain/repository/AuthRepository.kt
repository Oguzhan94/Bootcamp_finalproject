package com.example.bootcampfinalproject.domain.repository

import com.example.bootcampfinalproject.util.ResponseState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun registerEmailAndPassword(email: String, password: String): ResponseState<FirebaseUser>
    suspend fun login(email: String, password: String): ResponseState<FirebaseUser>
    suspend fun logout()
    fun getCurrentUser(): Flow<ResponseState<FirebaseUser?>>
}