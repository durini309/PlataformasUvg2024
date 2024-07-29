package com.uvg.ejercicioslabs.ejercicios.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun ColumnAndRow(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SeccionPeliculas(
            titulo = "Accion",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        SeccionPeliculas(
            titulo = "Comedia",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        SeccionPeliculas(
            titulo = "Romance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Composable
fun ListadoDeCartas(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ElevatedCard(
            modifier = Modifier.size(100.dp)
        ) {
            Text("Hola")
        }
        ElevatedCard(
            modifier = Modifier.size(100.dp)
        ) {
            Text("Hola")
        }
        ElevatedCard(
            modifier = Modifier.size(100.dp)
        ) {
            Text("Hola")
        }
    }
}

@Composable
fun SeccionPeliculas(
    titulo: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = titulo)
        ListadoDeCartas(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewColumnAndRow() {
    EjerciciosLabsTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ColumnAndRow()
        }
    }
}