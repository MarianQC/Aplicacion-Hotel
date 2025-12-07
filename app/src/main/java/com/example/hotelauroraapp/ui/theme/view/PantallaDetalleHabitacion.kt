package com.example.hotelauroraapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelauroraapp.model.obtenerHabitacionesDetalle
import com.example.hotelauroraapp.ui.theme.AzulHotel

@Composable
fun PantallaDetalleHabitacion(navController: NavController, habitacionId: Int) {
    val habitaciones = obtenerHabitacionesDetalle()
    val habitacion = habitaciones.find { it.id == habitacionId }

    Scaffold { innerPadding ->
        if (habitacion != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Título de la habitación
                Text(
                    text = habitacion.nombre,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulHotel,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Imagen de la habitación
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = habitacion.imagenRes),
                        contentDescription = habitacion.nombre,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Precio y capacidad
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = AzulHotel)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Precio",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = habitacion.precio,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = AzulHotel)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Capacidad",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = habitacion.capacidad,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Descripción
                Text(
                    text = "Descripción",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulHotel
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = habitacion.descripcion,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Amenidades
                Text(
                    text = "Amenidades",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulHotel
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Text(
                        text = habitacion.amenidades,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botón Volver
                Button(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulHotel)
                ) {
                    Text(
                        text = "VOLVER",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        } else {
            // En caso de que no se encuentre la habitación
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Habitación no encontrada",
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigateUp() },
                        colors = ButtonDefaults.buttonColors(containerColor = AzulHotel)
                    ) {
                        Text(text = "VOLVER")
                    }
                }
            }
        }
    }
}