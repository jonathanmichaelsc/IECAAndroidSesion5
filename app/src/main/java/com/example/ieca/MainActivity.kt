package com.example.ieca

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_temario = findViewById<Button>(R.id.btn_temario);
        btn_temario.setOnClickListener {
            startActivity(Intent(this, Temario::class.java))
        }

        val btn_calificaciones = findViewById<Button>(R.id.btn_calificaciones);
        btn_calificaciones.setOnClickListener {
            startActivity(Intent(this, Calificaciones::class.java))
        }

        val btn_registro = findViewById<Button>(R.id.btn_registro);
        btn_registro.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
        }

        val viewPager: ViewPager = findViewById(R.id.viewpager)
        val adapter = ViewpagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        requestCameraPermission()

        // ALMACENAMIENTO INTERNO
        escribirDatosAlmacenamientoInterno(
            nombreArchivoAlmacenamientoInterno,
            datosAlmacenamientoInterno
        )

        // ALMACENAMIENTO EXTERNO
        escribirDatosAlmacenamientoExterno()

        // ALMACENAMIENTO EN CACHE
        escribirDatosAlmacenamientoCache(this, clave, valor)
    }

    // **********************************************************************************

    override fun onResume() {
        super.onResume()

        val contenidoInterno = leerDatosAlmacenamientoInterno(nombreArchivoAlmacenamientoInterno)
        Toast.makeText(this, contenidoInterno, Toast.LENGTH_LONG).show()

        val contenidoExterno = leerDatosAlmacenamientoExterno()
        Toast.makeText(this, contenidoExterno, Toast.LENGTH_LONG).show()

        val contenidoCache = leerDatosAlmacenamientoCache(this, clave)
        Toast.makeText(this, contenidoCache, Toast.LENGTH_LONG).show()
    }

    // **********************************************************************************

    // PERMISOS PARA EL USO DE LA CÁMARA

    private val CAMERA_PERMISSION_CODE = 1001

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permiso de la cámara otorgado.
        } else {
            // Permiso de la cámara no otorgado
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso otorgado.
                } else {
                    // Permiso denegado, volver a solicitarlo.
                }
            }
        }
    }

    // ALMACENAMIENTO INTERNO

    val nombreArchivoAlmacenamientoInterno = "mi_archivo.txt"
    val datosAlmacenamientoInterno = "Contenido almacenado internamente"

    fun escribirDatosAlmacenamientoInterno(nombreArchivo: String, datos: String) {
        val archivo = File(this.filesDir, nombreArchivo)
        archivo.writeText(datos)
    }

    fun leerDatosAlmacenamientoInterno(nombreArchivo: String): String {
        val archivo = File(this.filesDir, nombreArchivo)
        return archivo.readText()
    }

    // ALMACENAMIENTO EXTERNO

    val nombreArchivoAlmacenamientoExterno = "miarchivoAE.txt"
    val datosAlmacenamientoExterno = "Contenido almacenado externamente"

    private fun escribirDatosAlmacenamientoExterno() {
        val estado = isExternalStorageWritable()
        if (estado) {
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio, nombreArchivoAlmacenamientoExterno)
            try {
                FileOutputStream(archivo).use { it.write(datosAlmacenamientoExterno.toByteArray()) }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun leerDatosAlmacenamientoExterno(): String {
        val estado = isExternalStorageReadable()
        if (estado) {
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio, nombreArchivoAlmacenamientoExterno)
            var fileInputStream = FileInputStream(archivo)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine();text }() != null) {
                stringBuilder.append(text)
            }
            fileInputStream.close()
            return stringBuilder.toString()
        }
        return ""
    }

    private fun isExternalStorageWritable(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado
    }

    private fun isExternalStorageReadable(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado || Environment.MEDIA_MOUNTED_READ_ONLY == estado
    }

    // ALMACENAMIENTO CACHE
    val clave = "Clave"
    val valor = "Contenido almacenado en la memoria cache"

    fun escribirDatosAlmacenamientoCache(context: Context, clave: String, valor: String) {
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(clave, valor)
        editor.apply()
    }

    fun leerDatosAlmacenamientoCache(context: Context, clave: String): String? {
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        return sharedPreferences.getString(clave, null)
    }


}
