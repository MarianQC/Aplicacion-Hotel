package com.example.hotelauroraapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hotelauroraapp.data.entity.UsuarioEntity

@Dao
interface Usuariodao {

    // Registrar un nuevo usuario
    // Si la cédula ya existe, IGNORE evita que la app se caiga
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registrar(usuario: UsuarioEntity):Long

    // Validar login: busca un usuario por cédula y contraseña
    @Query("SELECT * FROM usuarios WHERE cedula = :cedula AND password = :password LIMIT 1")
    suspend fun login(cedula: String, password: String): UsuarioEntity?

    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsuarios(): List<UsuarioEntity>

}
