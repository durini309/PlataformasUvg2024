package com.uvg.ejercicioslabs.ejercicios.navigation.dogList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.ejercicioslabs.ejercicios.navigation.Dog
import com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile.DogProfileDestination
import com.uvg.ejercicioslabs.ejercicios.navigation.dogProfile.navigateToDogProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object DogListDestination

fun NavGraphBuilder.dogListScreen(
    onDogClick: (Int) -> Unit
) {
    composable<DogListDestination> {
        DogListRoute(
            onDogClick = onDogClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}