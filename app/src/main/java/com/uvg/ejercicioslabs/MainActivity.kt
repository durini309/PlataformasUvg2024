package com.uvg.ejercicioslabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ejercicioslabs.ejercicios.agosto9.PantallaConContador
import com.uvg.ejercicioslabs.ejercicios.agosto9.PantallaFormularioDinamico
import com.uvg.ejercicioslabs.ejercicios.componentes.LoginEPS
import com.uvg.ejercicioslabs.ejercicios.componentes.PantallaLoginEps
import com.uvg.ejercicioslabs.ejercicios.layouts.ColumnAndRow
import com.uvg.ejercicioslabs.laboratorios.lab4.SolucionLab4
import com.uvg.ejercicioslabs.ui.theme.EjerciciosLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjerciciosLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaLoginEps(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
