package com.uvg.ejercicioslabs.ejercicios.firebase.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home.homeScreen
import com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home.navigateToHome
import com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login.FirebaseLoginDestination
import com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login.loginScreen

@Composable
fun FirebaseApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FirebaseLoginDestination
    ) {
        loginScreen(navigateToHome = navController::navigateToHome)
        homeScreen()
    }
}

