package com.uvg.ejercicioslabs.ejercicios.bottomNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object BNHomeDestination: BNDestination

@Composable
fun BNHomeScreen(
    modifier: Modifier =  Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}