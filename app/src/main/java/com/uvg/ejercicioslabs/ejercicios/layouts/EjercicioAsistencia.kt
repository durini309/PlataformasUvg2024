package com.uvg.ejercicioslabs.ejercicios.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun EjercicioAsistencia(
    modifier: Modifier = Modifier
) {
    Columnas(modifier)
}

@Composable
fun Columnas(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
        )
        Fila3y4(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
        )
    }
}

@Composable
fun Fila3y4(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEjercicioAsistencia() {
    EjerciciosLabsTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            EjercicioAsistencia(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}