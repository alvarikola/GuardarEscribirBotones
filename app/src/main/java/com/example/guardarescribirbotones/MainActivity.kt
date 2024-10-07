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
import androidx.compose.ui.tooling.preview.Preview
import com.example.guardarescribirbotones.ui.theme.GuardarEscribirBotonesTheme
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.OutputStreamWriter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuardarEscribirBotonesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SaveTextToFile("ejemplo.txt")
                }
            }
        }
    }

    @Composable
    fun SaveTextToFile(nombreArchivo: String) {
        val texto = "Hola me llamo Alvaro"
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { Escribir(texto, nombreArchivo) }
            ) {
                Text("Guardar archivo")
            }
            Button(
                onClick = { Leer(nombreArchivo) }
            ) {
                Text("Leer archivo")
            }
        }
    }
    fun Escribir(texto: String, nombreArchivo: String) {
        val estadoAlmacenamiento = Environment.getExternalStorageState()

        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val flujoSalida = FileOutputStream(archivo, true)
                val writer = OutputStreamWriter(flujoSalida)
                writer.append(texto)
                writer.close()

                Log.i("DAM2", "Añadido en $directorio $nombreArchivo")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("DAM2", "Error al guardar")
            }
        }else {
            Log.i("DAM2", "No se pudo acceder al almacenamiento externo")
        }
    }

    fun Leer(nombreArchivo: String) {
        val estadoAlmacenamiento = Environment.getExternalStorageState()
        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val flujoEntrada = FileReader(archivo)
                val leer = BufferedReader(flujoEntrada)
                val contenidoArchivo = leer.readLines()
                leer.close()

                Log.i("DAM2", "Contenido del archivo: $contenidoArchivo")
                flujoEntrada.close()
                // File(directorio, nombreArchivo).delete() esto es para borrar y que no se acumulen los datos
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("DAM2", "Error al leer")
            }
        }else {
            Log.i("DAM2", "No se pudo acceder al almacenamiento externo")
        }
    }


}