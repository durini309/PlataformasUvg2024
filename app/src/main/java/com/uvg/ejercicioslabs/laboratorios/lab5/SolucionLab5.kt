package com.uvg.ejercicioslabs.laboratorios.lab5

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

data class Place(
    val id: Int,
    val name: String,
    val address: String,
    val schedule: String
)

val places = listOf(
    Place(id = 3961, name = "Rosemary Combs", address = "prodesset", schedule = "porta"),
    Place(id = 3961, name = "Rosemary Combs", address = "prodesset", schedule = "porta"),
    Place(id = 3961, name = "Rosemary Combs", address = "prodesset", schedule = "porta"),
    Place(id = 3961, name = "Rosemary Combs", address = "prodesset", schedule = "porta"),
    Place(id = 3961, name = "Rosemary Combs", address = "prodesset", schedule = "porta"),
    Place(id = 3961, name = "Rosemary Combs", address = "prodesset", schedule = "porta")
)

@Composable
fun PantallaSolucionLab5(
    modifier: Modifier = Modifier
) {
    SolucionLab5(
        modifier = modifier
    )
}

@Composable
private fun SolucionLab5(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        UpdateHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            onDownloadClick = {
                val playstoreIntent = Intent(ACTION_VIEW)
                playstoreIntent.data = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsappLinks")
                context.startActivity(playstoreIntent)
            }
        )
        ScreenTitle(
            modifier = Modifier
                .fillMaxWidth()
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(places) { place ->
                CardVisita(
                    name = place.name,
                    address = place.address,
                    schedule = place.schedule,
                    onStartClick = {
                        Toast.makeText(context, "Hola", Toast.LENGTH_SHORT).show()
                    },
                    onDetailsClick = { /*TODO*/ },
                    onDirectionsClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(400.dp)
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun UpdateHeader(
    modifier: Modifier = Modifier,
    onDownloadClick: () -> Unit
) {
    Row (
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box(modifier = Modifier
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
            contentAlignment = Alignment.Center
        ){
            Icon(
                Icons.Default.Update,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Text(text = "Actualizacion Disponible")
        TextButton(onClick = onDownloadClick) {
            Text(text = "Descargar")
        }
    }
}

@Composable
private fun ScreenTitle(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Viernes",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(text = "16 de Agosto")

        }
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Terminar jornada")
        }
    }
}

@Composable
private fun CardVisita(
    name: String,
    address: String,
    schedule: String,
    onStartClick: () -> Unit,
    onDetailsClick: () -> Unit,
    onDirectionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.Directions,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = address,
                style  = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = schedule,
                style  = MaterialTheme.typography.labelMedium
            )
            Row {
                Button(
                    onClick = onStartClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Iniciar")
                }
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Detalles")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSolucionLab5() {
    EjerciciosLabsTheme {
        Surface {
            PantallaSolucionLab5(modifier = Modifier.fillMaxSize())
        }
    }
}