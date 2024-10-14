package com.uvg.ejercicioslabs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo.DSAppContent
import com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo.DSAuthViewModel
import com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo.DSAuthStatus
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    private val authViewModel: DSAuthViewModel by viewModels { DSAuthViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            authViewModel.authStatus.value is DSAuthStatus.Loading
        }

        setContent {
            EjerciciosLabsTheme {
                Surface {
                    // Comentar si quieren probar algo mas.
                    DSAppContent(authViewModel)
                }
            }
        }
    }
}

