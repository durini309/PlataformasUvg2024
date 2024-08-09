package com.uvg.ejercicioslabs.ejercicios.agosto9

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaConContador(
    modifier: Modifier = Modifier
) {
    ContadorCorrecto(
        modifier = modifier
    )
}

@Composable
private fun ContadorIncorrecto(
    modifier: Modifier = Modifier
) {
    var contador = 0
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "Count: $contador",
            style = MaterialTheme.typography.displayLarge
        )
        Button(onClick = { contador++ }) {
            Text(text = "Incrementar")
        }
    }
}

@Composable
private fun ContadorCorrecto(
    modifier: Modifier = Modifier
) {
    var contador by rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "Count: $contador",
            style = MaterialTheme.typography.displayLarge
        )
        Button(onClick = { contador++ }) {
            Text(text = "Incrementar")
        }
    }
}