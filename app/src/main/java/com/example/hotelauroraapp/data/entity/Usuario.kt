package com.example.hotelauroraapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val cedula: String,   // la cédula será la clave primaria
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var celular: String,
    var password: String

)
