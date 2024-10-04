package com.uvg.ejercicioslabs.ejercicios.architecture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun AuthRoute(
    viewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AuthScreen(
        state = state,
        onEmailChange = { email ->
            viewModel.onEvent(AuthScreenEvent.EmailChange(email))
        },
        onPasswordChange = { password ->
            viewModel.onEvent(AuthScreenEvent.PasswordChange(password))
        },
        onLogInClick = {
            viewModel.onEvent(AuthScreenEvent.LoginClick)
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun AuthScreen(
    state: AuthScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            placeholder = {
                Text(text = "Email")
            },
            isError = state.hasError,
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            placeholder = {
                Text(text = "Password")
            },
            isError = state.hasError,
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (state.hasError) {
            Text(
                text = "Error, intente de nuevo",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onLogInClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text(text = "Iniciar sesión")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (state.isSuccess) {
            Text(
                text = "Login Exitoso!",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAuthScreen() {
    EjerciciosLabsTheme() {
        Surface {
            AuthRoute()
        }
    }
}