package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object FirebaseHomeDestination

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(FirebaseHomeDestination)
}

fun NavGraphBuilder.homeScreen() {
    composable<FirebaseHomeDestination> {
        HomeRoute()
    }
}