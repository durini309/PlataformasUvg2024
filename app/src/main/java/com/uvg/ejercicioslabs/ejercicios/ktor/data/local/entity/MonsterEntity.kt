package com.uvg.ejercicioslabs.ejercicios.ktor.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster

@Entity
data class MonsterEntity(
    @PrimaryKey val id: Int,
    val description: String,
    val dlc: Boolean,
    val image: String,
    val name: String
)

fun MonsterEntity.mapToMonsterModel(): Monster {
    return Monster(
        id = id,
        name = name,
        image = image,
        description = description,
        dlc = dlc
    )
}