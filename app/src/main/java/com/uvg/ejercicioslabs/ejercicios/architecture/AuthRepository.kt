package com.uvg.ejercicioslabs.ejercicios.architecture

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
}