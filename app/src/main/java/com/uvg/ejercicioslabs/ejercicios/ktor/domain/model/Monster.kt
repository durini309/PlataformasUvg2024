package com.uvg.ejercicioslabs.ejercicios.ktor.domain.model

data class Monster(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val dlc: Boolean
)
