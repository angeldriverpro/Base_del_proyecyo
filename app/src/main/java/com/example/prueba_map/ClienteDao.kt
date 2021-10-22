package com.example.prueba_map

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ClienteDao {
    @Query("SELECT * FROM cliente")
    fun getALl(): LiveData<List<Cliente>>

    @Query("SELECT * FROM cliente WHERE idCliente = :id")
    fun get(id : Int): LiveData<Cliente>

    /*@Query("SELECT * FROM cliente WHERE nombre = (:nombre) AND contraseña=(:contraseña)")
    fun login(nombre:String, contraseña: String)*/

    @Insert
    fun insertAll(vararg cliente: Cliente)

    @Update
    fun update(cliente:Cliente)



}