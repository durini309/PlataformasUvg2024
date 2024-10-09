package com.uvg.ejercicioslabs.ejercicios.datastore.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun DataStoreRoute(
    viewModel: DataStoreViewModel = viewModel(factory = DataStoreViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val nameAndEmailState by viewModel.nameAndEmailState.collectAsStateWithLifecycle()

    DataStoreScreen(
        state = state,
        nameAndEmail = nameAndEmailState,
        onEmailChange = { viewModel.onEvent(DataStoreScreenEvent.EmailChange(it)) },
        onNameChange = { viewModel.onEvent(DataStoreScreenEvent.NameChange(it)) },
        onKeyChange = { viewModel.onEvent(DataStoreScreenEvent.KeyChange(it)) },
        onSaveEmailClick = { viewModel.onEvent(DataStoreScreenEvent.SaveEmail) },
        onSaveNameClick = { viewModel.onEvent(DataStoreScreenEvent.SaveName) },
        onGetValueClick = { viewModel.onEvent(DataStoreScreenEvent.GetValueClick) })
}

@Composable
private fun DataStoreScreen(
    state: DataStoreScreenState,
    nameAndEmail: String?,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onKeyChange: (String) -> Unit,
    onSaveEmailClick: () -> Unit,
    onSaveNameClick: () -> Unit,
    onGetValueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)) {
            SaveValuesSection(
                email = state.email,
                name = state.name,
                onEmailChange = onEmailChange,
                onNameChange = onNameChange,
                onSaveEmailClick = onSaveEmailClick,
                onSaveNameClick = onSaveNameClick,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)) {
            GetKeySection(
                key = state.key,
                value = state.value,
                onKeyChange = onKeyChange,
                onGetValueClick = onGetValueClick,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)) {
            NameEmailSection(
                nameAndEmail = nameAndEmail,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun SaveValuesSection(
    email: String,
    name: String,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onSaveEmailClick: () -> Unit,
    onSaveNameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Guardar valores",
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                placeholder = {
                    Text("Email")
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onSaveEmailClick) {
                Text("Guardar")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = {
                    Text("Name")
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onSaveNameClick) {
                Text("Guardar")
            }
        }
    }
}

@Composable
private fun GetKeySection(
    key: String,
    value: String?,
    onKeyChange: (String) -> Unit,
    onGetValueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Obtener valor by Key",
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = key,
                onValueChange = onKeyChange,
                placeholder = {
                    Text("Key")
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onGetValueClick) {
                Text("Obtener")
            }
        }
        Text(text = value ?: "Vacio o no encontrado")
    }
}

@Composable
private fun NameEmailSection(
    nameAndEmail:String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Real time data",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = nameAndEmail ?: "Sin valores todavia")
    }
}

@Preview
@Composable
private fun PreviewDataStoreScreen() {
    EjerciciosLabsTheme() {
        Surface {
            DataStoreRoute()
        }
    }
}