package com.uvg.ejercicioslabs.ejercicios.architecture

import kotlinx.coroutines.delay

class LocalAuthRepository: AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        delay(2000L)
        return email == "jdurini@uvg.edu.gt"
    }
}