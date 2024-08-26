package com.uvg.ejercicioslabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.ejercicioslabs.laboratorios.LaboratoriosDestination
import com.uvg.ejercicioslabs.laboratorios.LaboratoriosRoute
import com.uvg.ejercicioslabs.ui.home.HomeDestination
import com.uvg.ejercicioslabs.ui.home.HomeRoute
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
                        startDestination = HomeDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        composable<HomeDestination> {
                            HomeRoute(
                                onEjerciciosClick = { },
                                onLaboratoriosClick = {
                                    navController.navigate(
                                        route = LaboratoriosDestination
                                    )
                                }
                            )
                        }
                        composable<LaboratoriosDestination> {
                            LaboratoriosRoute(
                                onLab4Click = { /*TODO*/ },
                                onLab5Click = { /*TODO*/ },
                                onLab6Click = { /*TODO*/ },
                                onNavigateBack = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}

