package com.example.bootcampfinalproject.util

sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Error(val message: String) : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
}