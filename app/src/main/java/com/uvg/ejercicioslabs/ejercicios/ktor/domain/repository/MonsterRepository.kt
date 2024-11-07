package com.uvg.ejercicioslabs.ejercicios.ktor.domain.repository

import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.DataError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result

interface MonsterRepository {
    suspend fun getAllMonsters(): Result<List<Monster>, DataError>
    suspend fun getOneMonster(id: Int): Result<Monster, DataError>
}