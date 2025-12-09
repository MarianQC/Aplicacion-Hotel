package com.example.hotelauroraapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelauroraapp.data.database.HotelDatabase
import com.example.hotelauroraapp.data.entity.UsuarioEntity
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val usuariodao = HotelDatabase.getDatabase(application).usuariodao()

    fun registrarUsuario(usuario: UsuarioEntity, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val resultado = usuariodao.registrar(usuario)
            if (resultado == -1L) {
                onResult(false, "Ya existe un usuario con esa cédula")
            } else {
                onResult(true, "Usuario registrado correctamente")
            }
        }
    }

    fun loginUsuario(cedula: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val usuario = usuariodao.login(cedula, password)
            if (usuario != null) {
                onResult(true, "Login exitoso")
            } else {
                onResult(false, "Credenciales incorrectas")
            }
        }
    }
    init {
        viewModelScope.launch {
            // Esto fuerza a Room a abrir la base
            val count = usuariodao.getAllUsuarios().size
            android.util.Log.d("DB", "Usuarios en DB: $count")
        }
    }

}
