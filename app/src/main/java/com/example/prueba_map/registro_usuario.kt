package com.example.prueba_map

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.prueba_map.databinding.ActivityLoginBinding
import com.example.prueba_map.databinding.ActivityRegistroUsuarioBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class registro_usuario : AppCompatActivity() {
    //lateinit var clienteDBHelper : miSQLineHelper
    private lateinit var binding : ActivityRegistroUsuarioBinding
    lateinit var contraseña: String
    lateinit var correo: String
    lateinit var ciudad:String
    lateinit var nombre:String
    var telefono:Int? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val pref =applicationContext.getSharedPreferences("datos", 0)

        binding.btnRegistrarmeFinal.setOnClickListener{
            val editor = pref.edit()
            if (binding.etNombre.text.isNotBlank() && binding.etCorreo.text.isNotBlank()&& binding.etCiudad.text.isNotBlank()
                && binding.etTelefono.text.isNotBlank()&& binding.etContraseA.text.isNotBlank()){
                nombre = binding.etNombre.text.toString()
                correo = binding.etCorreo.text.toString()
                ciudad = binding.etCiudad.text.toString()
                //telefono = binding.etTelefono.text.toString().toInt()
                contraseña = binding.etContraseA.text.toString()

                editor.putString("nombre ${correo}", nombre)
                editor.putString("correo ${correo}", correo)
                editor.putString("ciudad ${correo}", ciudad)
                //editor.putInt("telefono ${correo}", telefono)
                editor.putString("contraseña ${correo}", contraseña)

                editor.apply()

                binding.etNombre.text.clear()
                binding.etCorreo.text.clear()
                binding.etCiudad.text.clear()
                binding.etTelefono.text.clear()
                binding.etContraseA.text.clear()

                Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, login::class.java)

                startActivity(intent)


            }




        }








       /* clienteDBHelper = miSQLineHelper(this)

        binding.btnRegistrarmeFinal.setOnClickListener{
            if (binding.etNombre.text.isNotBlank() && binding.etCorreo.text.isNotBlank()&& binding.etCiudad.text.isNotBlank()
                && binding.etTelefono.text.isNotBlank()&& binding.etContraseA.text.isNotBlank()){
                clienteDBHelper.InsetarCliente(binding.etNombre.text.toString(),binding.etCorreo.text.toString(),binding.etCiudad.text.toString(),binding.etTelefono.text.toString().toInt(),binding.etContraseA.text.toString())
                /*Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                val intent2 = Intent(this,home_cliente::class.java)
                val bundle = Bundle()

                bundle.putString("correo",binding.etCorreo.text.toString())
                intent2.putExtras(bundle)
                startActivity(intent2)*/

                val db: SQLiteDatabase = clienteDBHelper.readableDatabase

                val cursor = db.rawQuery("SELECT * FROM cliente",null)

                if (cursor.moveToFirst()){
                    do{
                        val correo = cursor.getString(2)
                        Toast.makeText(this, correo, Toast.LENGTH_SHORT).show()

                    }while (cursor.moveToNext())
                }

            }
            else  Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()

        }*/




       /* var idCLiente : Int? = null

        if(intent.hasExtra("cliente")){
            val cliente = intent.extras?.getSerializable("cliente")as Cliente

            binding.etNombre.setText(cliente.nombre)
            binding.etCorreo.setText(cliente.correo)
            binding.etCiudad.setText(cliente.pais)
            binding.etTelefono.setText(cliente.telefono)
            binding.etContraseA.setText(cliente.contraseña)

        }
        val database = AppDatabase.getDatabase(this)

        binding.btnRegistrarmeFinal.setOnClickListener{
            val nombre = binding.etNombre.toString()
            val correo = binding.etCorreo.toString()
            val ciudad = binding.etCiudad.toString()
            val telefono = binding.etTelefono.toString()
            val contraseña = binding.etContraseA.toString()

            val cliente = Cliente(nombre,correo,ciudad,telefono,contraseña)

            if(idCLiente!= null){
                CoroutineScope(Dispatchers.IO).launch {
                    cliente.idCliente = idCLiente
                    database.clientes().update(cliente)
                    this@registro_usuario.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.clientes().insertAll(cliente)
                    this@registro_usuario.finish()
                }
            }
        }*/

    }
}