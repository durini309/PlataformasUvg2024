package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterProfile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class MonsterProfileDestination(
    val id: Int
)

fun NavController.navigateToMonsterProfile(
    monsterId: Int
) {
    this.navigate(MonsterProfileDestination(
        id = monsterId
    ))
}

fun NavGraphBuilder.monsterProfile(
    onNavigateBack: () -> Unit
) {
    composable<MonsterProfileDestination> {
        MonsterProfileRoute(onNavigateBack = onNavigateBack)
    }
}