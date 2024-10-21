package com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto

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
