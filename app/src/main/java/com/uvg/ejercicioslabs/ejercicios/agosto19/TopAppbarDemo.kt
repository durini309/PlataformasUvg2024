package com.uvg.ejercicioslabs.ejercicios.agosto19

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbarDemo(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        TopAppBar(
            title = {
                Text("Home")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                       Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
        Row {
            FilterChip(
                selected = true,
                onClick = { /*TODO*/ },
                label = { Text("Nombre") }
            )
            FilterChip(
                selected = false,
                onClick = { /*TODO*/ },
                label = { Text("Nombre") }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTopAppbarDemo() {
    EjerciciosLabsTheme {
        Surface {
            TopAppbarDemo(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}