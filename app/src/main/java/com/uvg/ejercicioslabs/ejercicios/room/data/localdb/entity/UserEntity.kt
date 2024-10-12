package com.uvg.ejercicioslabs.ejercicios.room.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.ejercicioslabs.ejercicios.room.domain.model.User

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
    val country: String?
)

fun UserEntity.mapToModel(): User {
    return User(
        id = id,
        name = name,
        age = age,
        country = country ?: "Sin país"
    )
}

fun User.mapToEntity(): UserEntity {
    return UserEntity(
        name = name,
        age = age,
        country = country
    )
}
