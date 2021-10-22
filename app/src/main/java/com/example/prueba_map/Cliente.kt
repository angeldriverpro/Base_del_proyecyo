package com.example.prueba_map

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "cliente")
class Cliente (
    val nombre : String,
    var correo : String,
    val pais : String,
    val telefono : String,
    val contraseña : String,
    @PrimaryKey(autoGenerate = true)
    var idCliente : Int = 0

): Serializable