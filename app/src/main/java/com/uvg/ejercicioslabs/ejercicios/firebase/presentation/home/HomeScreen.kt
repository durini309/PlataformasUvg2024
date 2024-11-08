package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.Task
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskFilter
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.model.TaskStatus
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(uiState.error) {
        if (uiState.error != null) {
            Toast.makeText(
                context,
                uiState.error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    HomeScreen(
        state = uiState,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun HomeScreen(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create Task Section (30% height)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "New Task",
                style = MaterialTheme.typography.headlineSmall,

            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.newTaskTitle,
                    onValueChange = { onEvent(HomeScreenEvent.TitleChanged(it)) },
                    label = { Text("Title") },
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = { onEvent(HomeScreenEvent.AddTaskClicked) },
                    enabled = state.newTaskTitle.isNotBlank()
                ) {
                    Text("Add")
                }
            }
        }

        HorizontalDivider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
        ) {
            // Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TaskFilter.entries.forEach { status ->
                    FilterChip(
                        selected = state.selectedFilter == status,
                        onClick = { onEvent(HomeScreenEvent.FilterChanged(status)) },
                        label = { Text(status.name) }
                    )
                }
            }

            // Tasks List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.tasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onStatusChange = {
                            println("checked: $it")
                            onEvent(HomeScreenEvent.TaskStatusChanged(task.id, it))
                        },
                        onDelete = {
                            onEvent(HomeScreenEvent.DeleteTaskClicked(task.id))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onStatusChange: (TaskStatus) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.status == TaskStatus.FINISHED,
            onCheckedChange = { isChecked ->
                onStatusChange(if (isChecked) TaskStatus.FINISHED else TaskStatus.PENDING)
            }
        )

        Text(
            text = task.title,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete task"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val previewState = HomeScreenState(
        newTaskTitle = "",
        tasks = listOf(
            Task(id = "1", title = "Sample Task 1", status = TaskStatus.PENDING),
            Task(id = "2", title = "Sample Task 2", status = TaskStatus.FINISHED)
        )
    )

    EjerciciosLabsTheme {
        HomeScreen(
            state = previewState,
            onEvent = {}
        )
    }
}