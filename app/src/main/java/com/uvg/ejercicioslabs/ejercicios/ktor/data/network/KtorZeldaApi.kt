package com.uvg.ejercicioslabs.ejercicios.ktor.data.network

import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.EntryListDto
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.EntryProfileDto
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.util.safeCall
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.ZeldaApi
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.NetworkError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorZeldaApi(
    private val httpClient: HttpClient
): ZeldaApi {
    override suspend fun getAllMonsters(): Result<EntryListDto, NetworkError> {
        return safeCall<EntryListDto> {
            httpClient.get(
                "https://botw-compendium.herokuapp.com/api/v3/compendium/category/monsters"
            )
        }
    }

    override suspend fun getMonsterProfile(id: Int): Result<EntryProfileDto, NetworkError> {
        return safeCall<EntryProfileDto> {
            httpClient.get(
                "https://botw-compendium.herokuapp.com/api/v3/compendium/entry/{id}"
            )
        }
    }
}