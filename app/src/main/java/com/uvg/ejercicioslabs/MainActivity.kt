package com.uvg.ejercicioslabs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.uvg.ejercicioslabs.ejercicios.navDrawer.NavigationDrawerMainScreen
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjerciciosLabsTheme {
                NavigationDrawerMainScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

