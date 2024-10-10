package com.uvg.ejercicioslabs.ejercicios.datastore.presentation.authdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object DSHomeDestination

@Composable
fun DSHomeScreen(onLogOutClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.displayMedium,
        )
        Button(onClick = onLogOutClick) {
            Text(text = "Log Out")
        }
    }
}