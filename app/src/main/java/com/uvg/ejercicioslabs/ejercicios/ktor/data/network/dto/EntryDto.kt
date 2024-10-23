package com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto

import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster
import kotlinx.serialization.Serializable

@Serializable
data class EntryDto(
    val category: String,
    val description: String,
    val dlc: Boolean,
    val id: Int,
    val image: String,
    val name: String
)

fun EntryDto.mapToMonsterModel(): Monster {
    return Monster(
        id = id,
        name = name,
        image = image,
        description = description,
        dlc = dlc
    )
}