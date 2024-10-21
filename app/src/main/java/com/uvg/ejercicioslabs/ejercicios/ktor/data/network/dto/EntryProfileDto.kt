package com.uvg.ejercicioslabs.ejercicios.ktor.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class EntryProfileDto(
    val data: EntryDto,
    val message: String,
    val status: Int
)
