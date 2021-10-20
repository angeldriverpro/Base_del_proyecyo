package com.example.prueba_map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BtnCliente: Button = findViewById(R.id.BtnCliente)
        val BtnBarberia: Button = findViewById(R.id.BtnBarberia)

        BtnCliente.setOnClickListener{

            val intent = Intent(this,login::class.java)

            val bundle = Bundle()
            bundle.putString("actor","cliente")
            intent.putExtras(bundle)
            startActivity(intent)
        }
        BtnBarberia.setOnClickListener{

            val intent2 = Intent(this,login::class.java)

            val bundle2 = Bundle()
            bundle2.putString("actor","barberia")
            intent2.putExtras(bundle2)
            startActivity(intent2)
        }




    }
}