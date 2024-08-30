package com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.ejercicios.navigation.BreedSize
import com.uvg.ejercicioslabs.ejercicios.navigation.getBreedSizeFromId
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun DogProfileRoute(
    id: Int,
    name: String,
    onNavigateBack: () -> Unit,
) {
    val breedSize = getBreedSizeFromId(id)
    DogProfileScreen(
        id = id,
        name = name,
        breedSize = breedSize,
        onNavigateBack = onNavigateBack,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DogProfileScreen(
    id: Int,
    name: String,
    breedSize: BreedSize,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(text = name) },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "ID: $id")
                Text(text = "Name: $name")
                Text(text = "BreedSize: $breedSize")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDogProfileScreen() {
    EjerciciosLabsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            DogProfileScreen(
                id = 1,
                name = "Mike",
                breedSize = BreedSize.SMALL,
                onNavigateBack = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}