package com.uvg.ejercicioslabs.ejercicios.ktor.domain.network

import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.EntryListDto
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.EntryProfileDto
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.NetworkError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result

interface ZeldaApi {
    suspend fun getAllMonsters(): Result<EntryListDto, NetworkError>
    suspend fun getMonsterProfile(id: Int): Result<EntryProfileDto, NetworkError>
}