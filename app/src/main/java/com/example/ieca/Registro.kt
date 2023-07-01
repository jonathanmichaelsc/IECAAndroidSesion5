package com.example.ieca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class Registro : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var checkbox: CheckBox
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Vincular elementos de interfaz de usuario
        editTextName = findViewById(R.id.et_nombre)
        editTextEmail = findViewById(R.id.et_email)
        editTextPhone = findViewById(R.id.et_telefono)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        checkbox = findViewById(R.id.checkbox)

        // Establecer listener de clics para el botón
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val phone = editTextPhone.text.toString()

            // Validar campos
            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && checkbox.isChecked) {
                // Realizar alguna acción con los datos del formulario
                Toast.makeText(this, "Datos enviados: $name, $email, $phone", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val btn_inicio = findViewById<Button>(R.id.btn_inicio);
        btn_inicio.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}