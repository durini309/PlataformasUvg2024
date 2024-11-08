package com.uvg.ejercicioslabs.ejercicios.firebase.domain.repository

// LoginRepository.kt
interface LoginRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun createUser(email: String, password: String): Boolean
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logout()
}