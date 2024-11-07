package com.uvg.ejercicioslabs.ejercicios.ktor.data.repository

import com.uvg.ejercicioslabs.ejercicios.ktor.data.local.dao.MonsterDao
import com.uvg.ejercicioslabs.ejercicios.ktor.data.local.entity.mapToMonsterModel
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.mapToMonsterEntity
import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto.mapToMonsterModel
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.DataError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.ZeldaApi
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.NetworkError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.repository.MonsterRepository

class MonsterRepositoryImpl(
    private val zeldaApi: ZeldaApi,
    private val monsterDao: MonsterDao
): MonsterRepository {
    override suspend fun getAllMonsters(): Result<List<Monster>, DataError> {
        /*
            1. Tratar de obtener la data de internet.
            2. Si la data se obtuvo, entonces guardarla en base de datos
                a. Opcion 1: eliminar DB local y agregarla nuevamente
                b. Opcion 2: Upsert a la tabla
            3. Mappear la informacion y retornarla al ViewModel como un modelo
            4. Si la data no se obtuvo (error), entonces hay 2 opciones
                a. Opcion 1: retornar la data de DB Local
                b. Opcion 2: si no hay data, mostrar error
         */
        when (val result = zeldaApi.getAllMonsters()) {
            is Result.Error -> {
                println(result.error)

                val localMonsters = monsterDao.getAllMonsters()
                if (localMonsters.isEmpty()) {
                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    }

                    return Result.Error(
                        DataError.GENERIC_ERROR
                    )
                } else {
                    return Result.Success(
                        localMonsters.map { it.mapToMonsterModel() }
                    )
                }
            }
            is Result.Success -> {
                val remoteMonsters = result.data.data
                // Punto 2: exitoso
                monsterDao.insertMonsters(
                    remoteMonsters.map { it.mapToMonsterEntity() }
                )
                return Result.Success(
                    remoteMonsters.map { it.mapToMonsterModel() }
                )
            }
        }
    }

    override suspend fun getOneMonster(id: Int): Result<Monster, DataError> {
        /**
         * 1. Obtener registro de base de datos local
         */
        val localMonster = monsterDao.getMonsterById(id)
        return Result.Success(
            localMonster.mapToMonsterModel()
        )
    }
}