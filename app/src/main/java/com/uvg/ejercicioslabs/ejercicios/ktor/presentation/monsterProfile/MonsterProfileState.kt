package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterProfile

import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster

data class MonsterProfileState(
    val isLoading: Boolean,
    val monster: Monster
)
