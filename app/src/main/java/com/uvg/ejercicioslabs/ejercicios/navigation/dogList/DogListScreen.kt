package com.uvg.ejercicioslabs.ejercicios.navigation.dogList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.ejercicios.navigation.Dog
import com.uvg.ejercicioslabs.ejercicios.navigation.dogs

@Composable
fun DogListRoute(
    onDogClick: (Dog) -> Unit,
    modifier: Modifier = Modifier
) {
    DogListScreen(
        onDogClick = onDogClick,
        modifier = modifier
    )
}

@Composable
private fun DogListScreen(
    onDogClick: (Dog) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(dogs) { dog ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable { onDogClick(dog) }
            ) {
                Text(
                    text = dog.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}