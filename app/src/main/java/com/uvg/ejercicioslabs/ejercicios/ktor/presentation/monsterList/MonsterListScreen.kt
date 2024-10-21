package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterList

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun MonsterListRoute(
    viewModel: MonsterListViewModel = viewModel(factory = MonsterListViewModel.Factory)
) {
    viewModel.getMonsters()
}

@Preview
@Composable
private fun PreviewMonsterListScreen() {
    EjerciciosLabsTheme {
        Surface {
            MonsterListRoute()
        }
    }
}