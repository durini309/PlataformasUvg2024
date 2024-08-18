package com.uvg.ejercicioslabs.ejercicios.agosto12

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PantallaListadoElementos(
    modifier: Modifier = Modifier
) {
    val elements = remember {
        mutableStateListOf<String>()
    }
    var newElement by remember {
        mutableStateOf("")
    }
    val onAddElement = {
        if (newElement.isNotEmpty()) {
            elements.add(newElement)
            newElement = ""
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAddElement()
            }) {
                Icon(Icons.Outlined.Add, contentDescription = null)
            }
        }
    ) {
        ListadoElementos(
            newElement = newElement,
            elements = elements,
            onDeleteClick = elements::removeAt,
            onElementChange = { new -> newElement = new },
            onAddElement = onAddElement,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        )
    }
}

@Composable
private fun ListadoElementos(
    newElement: String,
    elements: MutableList<String>,
    onElementChange: (String) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onAddElement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Nuevo elemento",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newElement,
            onValueChange = onElementChange,
            trailingIcon = {
                IconButton(onClick = { onElementChange("")}) {
                    Icon(
                        Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onAddElement() }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Elementos",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (elements.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "Lista vacía",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            elements.forEachIndexed { index, s ->
                ElementoLista(
                    text = s,
                    onDeleteClick = { onDeleteClick(index) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(56.dp)
                )
            }
        }
        
    }
}

@Composable
fun ElementoLista(
    text: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        IconButton(onClick = onDeleteClick) {
            Icon(Icons.Outlined.Delete, contentDescription = null)
        }
    }
}