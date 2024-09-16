package com.uvg.ejercicioslabs.ejercicios.navDrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNDestination
import kotlinx.serialization.Serializable

@Serializable
data object NDMessagesDestination: NDDestination

@Composable
fun NDMessagesScreen(
    modifier: Modifier =  Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = "Messages",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}