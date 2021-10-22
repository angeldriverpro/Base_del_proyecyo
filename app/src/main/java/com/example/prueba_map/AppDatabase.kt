package com.example.prueba_map

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java
import kotlin.jvm.java as java1


@Database(entities = [Cliente::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientes() : ClienteDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase?= null

        fun getDatabase(context: Context):AppDatabase{
            val temInstance = INSTANCE

            if(temInstance!= null){
                return temInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "barberia"
                ).build()

                INSTANCE = temInstance

                return instance
            }

        }
    }
}