package com.uvg.ejercicioslabs.ejercicios.nestedNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
private data object LoginDestination

@Serializable
private data object DashboardDestination

@Serializable
private data object ProfileDestination

@Composable
fun BackStackExampleHavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        composable<LoginDestination> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.displayLarge,
                )
                OutlinedButton(onClick = {
                    navController.navigate(
                        route = DashboardDestination
                    )
                }) {
                    Text("Login Con BackStack (1)")
                }
                OutlinedButton(onClick = {
                    navController.navigate(
                        route = DashboardDestination
                    ) {
                        popUpTo<LoginDestination>() {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Login Sin BackStack (2)")
                }
            }
        }
        composable<DashboardDestination> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Dashboard",
                    style = MaterialTheme.typography.displayLarge,
                )
                OutlinedButton(onClick = {
                    navController.navigate(route = ProfileDestination)
                }) {
                    Text("Profile")
                }
                Button(onClick = {
                    navController.navigateUp()
                }) {
                    Text("Back")
                }
            }
        }
        composable<ProfileDestination> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.displayLarge,
                )
                OutlinedButton(onClick = {
                    navController.navigate(
                        route = LoginDestination
                    ) {
                        popUpTo<LoginDestination> {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Logout (1)")
                }
                OutlinedButton(onClick = {
                    navController.navigate(
                        route = LoginDestination
                    ) {
                        popUpTo(0)
                    }
                }) {
                    Text("Logout (2)")
                }
                Button(onClick = {
                    navController.navigateUp()
                }) {
                    Text("Back")
                }
            }
        }
    }
}