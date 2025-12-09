package com.example.hotelauroraapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hotelauroraapp.data.entity.UsuarioEntity
import com.example.hotelauroraapp.data.dao.Usuariodao

@Database(entities = [UsuarioEntity::class], version = 1, exportSchema = false)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun usuariodao(): Usuariodao

    companion object {
        @Volatile
        private var INSTANCE: HotelDatabase? = null

        fun getDatabase(context: Context): HotelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotelDatabase::class.java,
                    "usuarios_db" // nombre del archivo de la base de datos
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
