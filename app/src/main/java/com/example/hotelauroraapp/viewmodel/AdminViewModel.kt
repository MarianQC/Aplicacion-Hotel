package com.example.hotelauroraapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelauroraapp.data.database.HotelDatabase
import com.example.hotelauroraapp.data.entity.UsuarioEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(application: Application) : AndroidViewModel(application) {
    private val usuariodao = HotelDatabase.getDatabase(application).usuariodao()
    private val adminDao = HotelDatabase.getDatabase(application).administradorDao()

    private val _usuarios = MutableStateFlow<List<UsuarioEntity>>(emptyList())
    val usuarios: StateFlow<List<UsuarioEntity>> = _usuarios

    fun loginAdmin(username: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val admin = adminDao.login(username, password)
            if (admin != null) {
                onResult(true, "Login de administrador exitoso")
            } else {
                onResult(false, "Credenciales de administrador incorrectas")
            }
        }
    }

    fun cargarUsuarios() {
        viewModelScope.launch {
            _usuarios.value = usuariodao.getAllUsuarios()
        }
    }

    fun agregarUsuario(usuario: UsuarioEntity, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val resultado = usuariodao.registrar(usuario)
            if (resultado == -1L) {
                onResult(false, "Ya existe un usuario con esa cédula")
            } else {
                cargarUsuarios()
                onResult(true, "Usuario agregado correctamente")
            }
        }
    }

    fun actualizarUsuario(usuario: UsuarioEntity, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                usuariodao.actualizar(usuario)
                cargarUsuarios()
                onResult(true, "Usuario actualizado correctamente")
            } catch (e: Exception) {
                onResult(false, "Error al actualizar usuario")
            }
        }
    }

    fun eliminarUsuario(usuario: UsuarioEntity, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                usuariodao.eliminar(usuario)
                cargarUsuarios()
                onResult(true, "Usuario eliminado correctamente")
            } catch (e: Exception) {
                onResult(false, "Error al eliminar usuario")
            }
        }
    }
}