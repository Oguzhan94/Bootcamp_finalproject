package com.example.bootcampfinalproject.domain.usecase.auth

import com.example.bootcampfinalproject.domain.repository.AuthRepository
import com.example.bootcampfinalproject.util.ResponseState
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val  authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): ResponseState<FirebaseUser> {
        return authRepository.registerEmailAndPassword(email, password)
    }

}