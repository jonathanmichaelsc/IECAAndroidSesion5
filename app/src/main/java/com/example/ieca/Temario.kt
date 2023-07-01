package com.example.ieca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Temario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temario)

        val btn_inicio = findViewById<Button>(R.id.btn_inicio);
        btn_inicio.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //Llamamos al contenedor "id/fragment_container" que se encuentra en "activity_main."
        val ejemplo_fragment = ExampleFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ejemplo_fragment)
            .commit()
    }
}