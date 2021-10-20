package com.example.prueba_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val textView: TextView = findViewById(R.id.textView)

        intent.extras?.getString("actor").let {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }


    }
}