package com.example.hotelauroraapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "administradores")
data class AdministradorEntity(
    @PrimaryKey val username: String,
    var password: String
)