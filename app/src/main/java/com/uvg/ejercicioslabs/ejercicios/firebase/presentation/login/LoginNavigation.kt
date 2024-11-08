package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object FirebaseLoginDestination

fun NavGraphBuilder.loginScreen(
    navigateToHome: () -> Unit
) {
    composable<FirebaseLoginDestination>{
        LoginRoute(onNextScreen = navigateToHome)
    }
}