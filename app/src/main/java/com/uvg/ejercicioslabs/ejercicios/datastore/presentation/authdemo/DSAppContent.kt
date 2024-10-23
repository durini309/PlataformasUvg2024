package com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme
import kotlinx.serialization.Serializable

@Serializable
data object DSSplashDestination

@Composable
fun DSAppContent(
    authViewModel: DSAuthViewModel = viewModel(factory = DSAuthViewModel.Factory)
) {
    val navController = rememberNavController()
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = DSSplashDestination
    ) {
        composable<DSSplashDestination> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Splash Screen",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        composable<DSLoginDestination> {
            DSLoginScreen(
                onLogInClick = authViewModel::login
            )
        }

        composable<DSHomeDestination> {
            DSHomeScreen(
                onLogOutClick = authViewModel::logout
            )
        }
    }

    LaunchedEffect(authStatus) {
        when (authStatus) {
            DSAuthStatus.Authenticated -> {
                navController.navigate(DSHomeDestination) {
                    popUpTo(DSLoginDestination) {
                        inclusive = true
                    }
                }
            }
            DSAuthStatus.NonAuthenticated -> {
                navController.navigate(DSLoginDestination) {
                    popUpTo(0)
                }
            }
            DSAuthStatus.Loading -> {}
        }
    }
}

@Preview
@Composable
private fun PreviewDSNavHost() {
    EjerciciosLabsTheme {
        Surface {
            DSAppContent()
        }
    }
}