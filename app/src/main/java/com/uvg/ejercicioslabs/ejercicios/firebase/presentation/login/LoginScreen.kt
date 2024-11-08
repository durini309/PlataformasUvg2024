package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    onNextScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        email = uiState.email,
        password = uiState.password,
        successMessage = uiState.successMessage,
        errorMessage = uiState.errorMessage,
        isLoading = uiState.isLoading,
        onEvent = viewModel::onEvent,
        onNextScreen = onNextScreen
    )
}


@Composable
private fun LoginScreen(
    email: String,
    password: String,
    successMessage: String?,
    errorMessage: String?,
    isLoading: Boolean,
    onEvent: (LoginScreenEvent) -> Unit,
    onNextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { onEvent(LoginScreenEvent.EmailChanged(it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { onEvent(LoginScreenEvent.PasswordChanged(it)) },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.VisibilityOff
                        else
                            Icons.Default.Visibility,
                        contentDescription = if (passwordVisible)
                            "Hide password"
                        else
                            "Show password"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Success/Error Messages
        AnimatedVisibility(
            visible = successMessage != null || errorMessage != null,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = successMessage ?: errorMessage ?: "",
                color = if (errorMessage != null) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Login and Create Account Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onEvent(LoginScreenEvent.LoginClicked) },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Login")
                }
            }

            Button(
                onClick = { onEvent(LoginScreenEvent.CreateAccountClicked) },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Create Account")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Auth Status and Logout Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onEvent(LoginScreenEvent.CheckAuthStatusClicked) },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Check Status")
            }

            Button(
                onClick = { onEvent(LoginScreenEvent.LogoutClicked) },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Logout")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { throw RuntimeException("Unhandled Exception") },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crash!")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNextScreen,
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next Screen")
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EjerciciosLabsTheme {
        LoginRoute(
            onNextScreen = {}
        )
    }
}