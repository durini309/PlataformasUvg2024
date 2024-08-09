package com.uvg.ejercicioslabs.ejercicios.agosto9

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.R
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

@Composable
fun PantallaFormularioDinamico(
    modifier: Modifier = Modifier
) {
    var Nombredelpunto by rememberSaveable {
        mutableStateOf("")
    }
    var Direccion by rememberSaveable {
        mutableStateOf("")
    }
    var EsCasa by rememberSaveable {
        mutableStateOf(false)
    }
    FormularioDinamico(
        modifier = modifier,
        nombreDelPunto = Nombredelpunto,
        direccionDelPunto = Direccion,
        esCasa = EsCasa,
        onNombreDelPuntoChange = { newText -> Nombredelpunto = newText },
        onDireccionDelPuntoChange = {newText -> Direccion = newText},
        onEsCasaChange = {EsCasa = !EsCasa}
    )
}

@Composable
private fun FormularioDinamico(
    nombreDelPunto: String,
    direccionDelPunto: String,
    esCasa: Boolean,
    onNombreDelPuntoChange: (String) -> Unit,
    onDireccionDelPuntoChange: (String) -> Unit,
    onEsCasaChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Actualización de datos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )
        OutlinedTextField(
            value = nombreDelPunto,
            onValueChange = onNombreDelPuntoChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Nombre del punto")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_grade),
                    contentDescription = ""
                )
            }
        )
        OutlinedTextField(
            value = direccionDelPunto,
            onValueChange = onDireccionDelPuntoChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Dirección")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null
                )
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = esCasa,
                onCheckedChange = {
                    onEsCasaChange()
                },
            )
            Text("Es direccion de casa")
        }
        Button(
            onClick = { /*TODO*/ },
            enabled = esCasa
        ) {
            Text("Agregar campo")
        }
    }
}

@Preview
@Composable
private fun PreviewFormularioDinamico() {
    EjerciciosLabsTheme {
        Surface {
            PantallaFormularioDinamico(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}