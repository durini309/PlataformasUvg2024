package com.uvg.ejercicioslabs.ejercicios.datastore.domain

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun setName(name: String)
    suspend fun setEmail(email: String)
    suspend fun getValue(key: String): String?
    fun getNameAndEmail(): Flow<String>
}