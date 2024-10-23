package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterProfile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute

class MonsterProfileViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val monsterProfile = savedStateHandle.toRoute<MonsterProfileDestination>()

    fun getId() = monsterProfile.id
}