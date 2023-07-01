package com.example.ieca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Calificaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificaciones)

        val btn_inicio = findViewById<Button>(R.id.btn_inicio);
        btn_inicio.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}