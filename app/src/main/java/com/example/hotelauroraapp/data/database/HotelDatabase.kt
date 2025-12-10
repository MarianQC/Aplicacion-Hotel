package com.example.hotelauroraapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hotelauroraapp.data.entity.UsuarioEntity
import com.example.hotelauroraapp.data.entity.AdministradorEntity
import com.example.hotelauroraapp.data.dao.Usuariodao
import com.example.hotelauroraapp.data.dao.Administradordao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [UsuarioEntity::class, AdministradorEntity::class],
    version = 2,
    exportSchema = false
)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun usuariodao(): Usuariodao
    abstract fun administradorDao(): Administradordao

    companion object {
        @Volatile
        private var INSTANCE: HotelDatabase? = null

        fun getDatabase(context: Context): HotelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotelDatabase::class.java,
                    "hotel_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Insertar administrador por defecto
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    database.administradorDao().login("admin", "admin123")
                                    db.execSQL("INSERT INTO administradores (username, password) VALUES ('admin', 'admin123')")
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}