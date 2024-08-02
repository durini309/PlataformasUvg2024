package com.uvg.ejercicioslabs.ejercicios.componentes

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.ejercicioslabs.R
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun CardEjercicio() {
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.mipmap.logo_eps_2),
                contentDescription = "x",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(0.6f)
                .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
            Text(
                text = "Arroz",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = "Tres cultivos disponibles")

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(
                        onClick = { /*TODO*/ },

                    ) {
                    Text(text = "ver")
                }
            }

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCardEjercicio() {
    EjerciciosLabsTheme() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CardEjercicio()
        }
    }
}