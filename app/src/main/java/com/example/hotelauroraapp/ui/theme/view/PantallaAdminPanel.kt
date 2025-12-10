package com.example.hotelauroraapp.ui.theme.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelauroraapp.data.entity.UsuarioEntity
import com.example.hotelauroraapp.viewmodel.AdminViewModel
import com.example.hotelauroraapp.ui.theme.AzulHotel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAdminPanel(viewModel: AdminViewModel, navController: NavController) {
    val usuarios by viewModel.usuarios.collectAsState()
    var mostrarDialogoAgregar by remember { mutableStateOf(false) }
    var usuarioAEditar by remember { mutableStateOf<UsuarioEntity?>(null) }
    var mostrarDialogoEliminar by remember { mutableStateOf<UsuarioEntity?>(null) }

    LaunchedEffect(Unit) {
        viewModel.cargarUsuarios()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Panel de Administración",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulHotel
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarDialogoAgregar = true },
                containerColor = AzulHotel
            ) {
                Text("+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Usuarios Registrados (${usuarios.size})",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulHotel,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            if (usuarios.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text(
                            text = "No hay usuarios registrados",
                            modifier = Modifier.padding(32.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                items(usuarios) { usuario ->
                    TarjetaUsuario(
                        usuario = usuario,
                        onEditar = { usuarioAEditar = usuario },
                        onEliminar = { mostrarDialogoEliminar = usuario }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    // Diálogo para agregar usuario
    if (mostrarDialogoAgregar) {
        DialogoFormularioUsuario(
            usuario = null,
            onDismiss = { mostrarDialogoAgregar = false },
            onConfirmar = { nuevoUsuario ->
                viewModel.agregarUsuario(nuevoUsuario) { _, _ ->
                    mostrarDialogoAgregar = false
                }
            }
        )
    }

    // Diálogo para editar usuario
    usuarioAEditar?.let { usuario ->
        DialogoFormularioUsuario(
            usuario = usuario,
            onDismiss = { usuarioAEditar = null },
            onConfirmar = { usuarioActualizado ->
                viewModel.actualizarUsuario(usuarioActualizado) { _, _ ->
                    usuarioAEditar = null
                }
            }
        )
    }

    // Diálogo de confirmación de eliminación
    mostrarDialogoEliminar?.let { usuario ->
        AlertDialog(
            onDismissRequest = { mostrarDialogoEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de que deseas eliminar al usuario ${usuario.nombres} ${usuario.apellidos}?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.eliminarUsuario(usuario) { _, _ ->
                            mostrarDialogoEliminar = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun TarjetaUsuario(
    usuario: UsuarioEntity,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${usuario.nombres} ${usuario.apellidos}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulHotel
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Cédula: ${usuario.cedula}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Email: ${usuario.correo}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Celular: ${usuario.celular}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row {
                    Button(
                        onClick = onEditar,
                        colors = ButtonDefaults.buttonColors(containerColor = AzulHotel),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Editar")
                    }
                    Button(
                        onClick = onEliminar,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}

@Composable
fun DialogoFormularioUsuario(
    usuario: UsuarioEntity?,
    onDismiss: () -> Unit,
    onConfirmar: (UsuarioEntity) -> Unit
) {
    var nombres by remember { mutableStateOf(usuario?.nombres ?: "") }
    var apellidos by remember { mutableStateOf(usuario?.apellidos ?: "") }
    var cedula by remember { mutableStateOf(usuario?.cedula ?: "") }
    var correo by remember { mutableStateOf(usuario?.correo ?: "") }
    var celular by remember { mutableStateOf(usuario?.celular ?: "") }
    var password by remember { mutableStateOf(usuario?.password ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (usuario == null) "Agregar Usuario" else "Editar Usuario",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = nombres,
                        onValueChange = { nombres = it },
                        label = { Text("Nombres") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
                item {
                    OutlinedTextField(
                        value = apellidos,
                        onValueChange = { apellidos = it },
                        label = { Text("Apellidos") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
                item {
                    OutlinedTextField(
                        value = cedula,
                        onValueChange = { cedula = it },
                        label = { Text("Cédula") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = usuario == null,
                        singleLine = true
                    )
                }
                item {
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
                item {
                    OutlinedTextField(
                        value = celular,
                        onValueChange = { celular = it },
                        label = { Text("Celular") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
                item {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val usuarioNuevo = UsuarioEntity(
                        cedula = cedula,
                        nombres = nombres,
                        apellidos = apellidos,
                        correo = correo,
                        celular = celular,
                        password = password
                    )
                    onConfirmar(usuarioNuevo)
                },
                colors = ButtonDefaults.buttonColors(containerColor = AzulHotel)
            ) {
                Text(if (usuario == null) "Agregar" else "Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}