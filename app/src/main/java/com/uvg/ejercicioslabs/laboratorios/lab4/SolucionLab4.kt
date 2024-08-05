package com.uvg.ejercicioslabs.laboratorios.lab4

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.R
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

private val GreenUVG = Color(0xFF006E1C)

@Composable
fun SolucionLab4(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 8.dp,
                color = GreenUVG
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_eps_2),
            contentDescription = null,
            alpha = 0.10f,
            modifier = Modifier.padding(32.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Text(
                text = "Universidad del Valle de Guatemala",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Programación de plataformas móviles, Sección 30",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "INTEGRANTES",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.Center
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(text = "Juan Durini")
                    Text(text = "Carlos Serrano")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "CATEDRÁTICO",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Juan Carlos Durini",
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Juan Carlos Durini")
                Text(text = "1201613")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSolucion4Lab() {
    EjerciciosLabsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SolucionLab4(modifier = Modifier.fillMaxSize())
        }
    }
}