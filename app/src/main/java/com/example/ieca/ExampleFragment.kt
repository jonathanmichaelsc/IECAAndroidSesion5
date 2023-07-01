package com.example.ieca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ExampleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_example_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView1 = view.findViewById<TextView>(R.id.tv_titulo_fragment)
        val textView2 = view.findViewById<TextView>(R.id.tv_definicion_fragment)
        val button = view.findViewById<Button>(R.id.btn_texto)

        button.setOnClickListener {
            textView1.text = "El botón reemplaza el texto del id tv_titulo_fragment"
        }
    }
}