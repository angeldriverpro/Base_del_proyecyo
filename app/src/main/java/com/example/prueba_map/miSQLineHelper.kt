package com.example.prueba_map

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class miSQLineHelper(context: Context):SQLiteOpenHelper
    (context,"clientes.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
       val ordenCreacion = "CREATE TABLE cliente"+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
               "nombre TEXT, " +
               "correo TEXT," +
               "pais TEXT," +
               "telefono INT," +
               "contraseña TEXT )"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       val ordenBorrado = "DROP TABLE IF EXISTS cliente"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun InsetarCliente(nombre: String, correo:String, pais: String, telefono:Int, contraseña: String){
        val datos = ContentValues()
        datos.put("nombre",nombre)
        datos.put("correo",correo)
        datos.put("pais",pais)
        datos.put("telefono", telefono)
        datos.put("contraseña",contraseña)

        val db = this.writableDatabase
        db.insert("cliente",null,datos)
        db.close()
    }

}