package com.uvg.ejercicioslabs.laboratorios.lab6

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

enum class ChangeType {
    ADDITION,
    SUBTRACTION
}

data class HistoryItem(
    val currentValue: Int,
    val changeType: ChangeType
)

data class ScreenState(
    val counter: Int = 0,
    val totalInc: Int = 0,
    val totalDec: Int = 0,
    val maxVal: Int = 0,
    val minVal: Int = 0,
    val history: List<HistoryItem> = listOf()
)

@Composable
fun PantallaSolucionLab6(
    modifier: Modifier = Modifier
) {
    var screenState by remember {
        mutableStateOf(ScreenState())
    }
    SolucionLab6(
        state = screenState,
        onIncrementClick = {
            val newValue = screenState.counter + 1
            screenState = screenState.copy(
                counter = newValue,
                maxVal = if (screenState.counter == screenState.maxVal) {
                    newValue
                } else {
                    screenState.maxVal
                },
                history = screenState.history + HistoryItem(
                    currentValue = newValue,
                    changeType = ChangeType.ADDITION
                ),
                totalInc = screenState.totalInc + 1
            )
        },
        onDecrementClick = {
            val newValue = screenState.counter - 1
            screenState = screenState.copy(
                counter = newValue,
                minVal = if (screenState.counter == screenState.minVal) {
                    newValue
                } else {
                    screenState.minVal
                },
                history = screenState.history + HistoryItem(
                    currentValue = newValue,
                    changeType = ChangeType.SUBTRACTION
                ),
                totalDec = screenState.totalDec + 1
            )
        },
        onResetClick = { screenState = ScreenState() },
        modifier = modifier
    )
}

@Composable
private fun CounterSection(
    counterValue: Int,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FilledIconButton(onClick = onDecrementClick) {
            Icon(Icons.Default.Remove, contentDescription = "Restar")
        }
        Text(
            text = counterValue.toString(),
            style = MaterialTheme.typography.displayLarge,
            fontSize = 80.sp
        )
        FilledIconButton(onClick = onIncrementClick) {
            Icon(Icons.Default.Add, contentDescription = "Sumar")
        }
    }
}

@Composable
private fun StatisticsItem(
    text: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun HistorySection(
    history: List<HistoryItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = "Historial:",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        if (history.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Text(
                    text = "Sin movimientos",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(history) { item ->
                    OutlinedCard {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(
                                    if (item.changeType == ChangeType.ADDITION) {
                                        Color(0xFF1c7d15)
                                    } else {
                                        MaterialTheme.colorScheme.error
                                    }
                                )
                        ) {
                            Text(
                                text = item.currentValue.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.align(Alignment.Center),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SolucionLab6(
    state: ScreenState,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Juan Carlos Durini",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            CounterSection(
                counterValue = state.counter,
                onIncrementClick = onIncrementClick,
                onDecrementClick = onDecrementClick,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatisticsItem(
                    text = "Total incrementos:",
                    value = state.totalInc,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                StatisticsItem(
                    text = "Total decrementos:",
                    value = state.totalDec,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                StatisticsItem(
                    text = "Valor máximo:",
                    value = state.maxVal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                StatisticsItem(
                    text = "Valor mínimo:",
                    value = state.minVal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                StatisticsItem(
                    text = "Total cambios:",
                    value = state.history.count(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                HistorySection(history = state.history)
            }
        }
        Button(
            onClick = onResetClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Reiniciar")
        }
    }
}

@Preview
@Composable
private fun PreviewSolucionLab6() {
    EjerciciosLabsTheme {
        Surface {
            SolucionLab6(
                state = ScreenState(
                    counter = 10,
                    totalInc = 20,
                    totalDec = 10,
                    history = listOf(
                        HistoryItem(
                            currentValue = 1,
                            changeType = ChangeType.ADDITION
                        )
                    )
                ),
                onIncrementClick = {},
                onDecrementClick = {},
                onResetClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}