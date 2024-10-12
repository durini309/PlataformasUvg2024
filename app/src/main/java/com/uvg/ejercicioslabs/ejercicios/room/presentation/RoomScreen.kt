package com.uvg.ejercicioslabs.ejercicios.room.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.ejercicios.room.domain.model.User

@Composable
fun RoomRoute(viewModel: RoomViewModel = viewModel(factory = RoomViewModel.Factory)) {
    val users by viewModel.users.collectAsStateWithLifecycle()
    val userName by viewModel.userName.collectAsStateWithLifecycle()
    RoomScreen(
        users = users,
        userName = userName,
        onUserNameChange = viewModel::updateUserName,
        onAddClick = viewModel::addUser,
        onDeleteClick = viewModel::deleteUser,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun RoomScreen(
    users: List<User>,
    userName: String,
    onUserNameChange: (String) -> Unit,
    onAddClick: () -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = userName,
                onValueChange = onUserNameChange,
                label = { Text("Nombre de usuario") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onAddClick) {
                Text("Agregar")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(users) { user ->
                RoomUserItem(user = user, onDeleteClick = onDeleteClick)
            }
        }
    }
}

@Composable
fun RoomUserItem(user: User, onDeleteClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = user.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Edad: ${user.age}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "País: ${user.country}", style = MaterialTheme.typography.labelMedium)
        }
        IconButton(onClick = { onDeleteClick(user.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar usuario")
        }
    }
}
