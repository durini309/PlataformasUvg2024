package com.uvg.ejercicioslabs.laboratorios

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme
import kotlinx.serialization.Serializable

@Serializable
data object LaboratoriosDestination

@Composable
fun LaboratoriosRoute(
    onLab4Click: () -> Unit,
    onLab5Click: () -> Unit,
    onLab6Click: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    LaboratoriosScreen(
        onLab4Click = onLab4Click,
        onLab5Click = onLab5Click,
        onLab6Click = onLab6Click,
        onNavigateBack = onNavigateBack,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaboratoriosScreen(
    onLab4Click: () -> Unit,
    onLab5Click: () -> Unit,
    onLab6Click: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Laboratorios"
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onLab4Click) {
                Text("Lab 4")
            }
            Button(onClick = onLab5Click) {
                Text("Lab 5")
            }
            Button(onClick = onLab6Click) {
                Text("Lab 6")
            }
        }

    }
}

@Preview
@Composable
private fun PreviewLaboratoriosScreen() {
    EjerciciosLabsTheme {
        Surface {
            LaboratoriosScreen(
                onLab4Click = {},
                onLab5Click = {},
                onLab6Click = {},
                onNavigateBack = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}