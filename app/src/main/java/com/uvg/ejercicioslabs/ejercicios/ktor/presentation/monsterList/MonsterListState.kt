package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterList

import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster

data class MonsterListState(
    val isLoading: Boolean = true,
    val data: List<Monster> = emptyList(),
    val isError: Boolean = false,
)
