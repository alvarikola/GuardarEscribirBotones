package com.example.guardarescribirbotones

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.guardarescribirbotones.ui.theme.GuardarEscribirBotonesTheme
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.OutputStreamWriter
import com.example.guardarescribirbotones.WriteReadFile


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.i("prueba", LocalContext.current.toString())
            GuardarEscribirBotonesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SaveTextToFile("ejemplo.txt")
                }
            }
        }
    }

    @Composable
    fun SaveTextToFile(nombreArchivo: String) {
        val texto = "Hola me llamo Alvaro"
        val myContext = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    var outs = WriteReadFile.guardarTextoEnArchivo(myContext, texto, nombreArchivo)
                    Log.i("prueba", outs)
                }
            ) {
                Text("Guardar archivo")
            }
            Button(
                onClick = {
                    var outs2 = WriteReadFile.leerTextoArchivo(myContext, nombreArchivo)
                    Log.i("prueba", outs2)
                }
            ) {
                Text("Leer archivo")
            }
        }
    }





}