package com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class DogProfileDestination(
    val dogId: Int,
    val dogName: String
)

fun NavController.navigateToDogProfileScreen(
    destination: DogProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.dogProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable<DogProfileDestination> { backStackEntry ->
        val destination: DogProfileDestination = backStackEntry.toRoute()
        DogProfileRoute(
            id = destination.dogId,
            name = destination.dogName,
            onNavigateBack = onNavigateBack
        )
    }
}