package com.uvg.ejercicioslabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.ejercicioslabs.ejercicios.navigation.dogList.DogListDestination
import com.uvg.ejercicioslabs.ejercicios.navigation.dogList.dogListScreen
import com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile.DogProfileDestination
import com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile.dogProfileScreen
import com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile.navigateToDogProfileScreen
import com.uvg.ejercicioslabs.ejercicios.nestedNavigation.BackStackExampleHavHost
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjerciciosLabsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = DogListDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        dogListScreen(
                            onDogClick = { id ->
                                navController.navigateToDogProfileScreen(
                                    destination = DogProfileDestination(
                                        dogId = id
                                    )
                                )
                            }
                        )
                        dogProfileScreen(
                            onNavigateBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

