package com.example.hotelauroraapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hotelauroraapp.data.entity.AdministradorEntity

@Dao
interface Administradordao {
    @Query("SELECT * FROM administradores WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): AdministradorEntity?
}