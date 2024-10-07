package com.example.guardarescribirbotones

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.OutputStreamWriter

class WriteReadFile {
    companion object{
        fun guardarTextoEnArchivo(context: Context, texto: String, nombreArchivo: String): String {
            val estadoAlmacenamiento = Environment.getExternalStorageState()

            if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
                val directorio = context.filesDir
                val archivo = File(directorio, nombreArchivo)
                try {
                    val flujoSalida = FileOutputStream(archivo, true)
                    val writer = OutputStreamWriter(flujoSalida)
                    writer.append(texto)
                    writer.close()

                    return "Texto añadido en $directorio $nombreArchivo"
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error al guardar"
                }
            }else {
                return "No se pudo acceder al almacenamiento externo"
            }
        }
        fun leerTextoArchivo(context: Context, nombreArchivo: String): String {
            val estadoAlmacenamiento = Environment.getExternalStorageState()
            if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
                val directorio = context.filesDir
                val archivo = File(directorio, nombreArchivo)
                try {
                    val flujoEntrada = FileReader(archivo)
                    val leer = BufferedReader(flujoEntrada)
                    val contenidoArchivo = leer.readLines()
                    leer.close()

                    return "Contenido del archivo: $contenidoArchivo"
                    flujoEntrada.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error al leer"
                }
            }else {
                return "No se pudo acceder al almacenamiento externo"
            }
        }
    }
}