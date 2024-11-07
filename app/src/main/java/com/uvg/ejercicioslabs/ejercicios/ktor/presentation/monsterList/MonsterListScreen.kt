package com.uvg.ejercicioslabs.ejercicios.ktor.presentation.monsterList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.uvg.ejercicioslabs.R
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.model.Monster
import com.uvg.ejercicioslabs.ejercicios.ktor.presentation.common.ErrorLayout
import com.uvg.ejercicioslabs.ejercicios.ktor.presentation.common.LoadingLayout
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun MonsterListRoute(
    onMonsterClick: (Int) -> Unit,
    viewModel: MonsterListViewModel = viewModel(factory = MonsterListViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MonsterListScreen(
        state = state,
        onRetryClick = viewModel::getMonsters,
        onMonsterClick = onMonsterClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun MonsterListScreen(
    state: MonsterListState,
    onRetryClick: () -> Unit,
    onMonsterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                LoadingLayout(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            state.isGenericError -> {
                ErrorLayout(
                    text = stringResource(R.string.error_fetching_data),
                    onRetryClick = onRetryClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.noInternetConnection -> {
                ErrorLayout(
                    text = stringResource(R.string.no_internect_connection),
                    onRetryClick = onRetryClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn {
                    items(state.data) { monster ->
                        MonsterItem(
                            monster = monster,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable { onMonsterClick(monster.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MonsterItem(
    monster: Monster,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = monster.image,
            contentDescription = monster.name,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Text(monster.name)
    }
}

@Preview
@Composable
private fun PreviewMonsterListScreen() {
    EjerciciosLabsTheme {
        Surface {
            MonsterListScreen(
                state = MonsterListState(
                    isLoading = false,
                    data = listOf(
                        Monster(
                            id = 7537,
                            name = "Chasity Torres",
                            image = "image",
                            description = "singulis",
                            dlc = false
                        ),
                        Monster(
                            id = 7537,
                            name = "Chasity Torres",
                            image = "image",
                            description = "singulis",
                            dlc = false
                        )
                    )
                ),
                onRetryClick = {},
                onMonsterClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}