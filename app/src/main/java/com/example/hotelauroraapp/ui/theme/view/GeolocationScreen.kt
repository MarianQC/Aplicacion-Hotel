package com.example.hotelauroraapp.ui.theme.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelauroraapp.ui.theme.AzulHotel

@Composable
fun GeolocationScreen(navController: NavController) {
    val context = LocalContext.current

    // Coordenadas del Hotel Aurora (ajusta según la ubicación real)
    val hotelLat = -2.1498850743362645 // NUEVA Latitud para 1er Callejon 15 NE
    val hotelLng = -79.88716503121469  // NUEVA Longitud para 1er Callejon 15 NE
    val hotelNombre = "Hotel Aurora"
    val hotelDireccion = "1er Callejon 15 NE, Guayaquil 090513, Ecuador"
    val hotelTelefono = "025034411" // Número sin guiones para Intent.ACTION_DIAL
    val hotelEmail = "aurorahotel@outlook.com"
    val gmmIntentUri = Uri.parse("ubicación:hotelLat,hotelLng?q=hotelLat,hotelLng(hotelNombre)")

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Ubicación",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = AzulHotel
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = hotelNombre,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = AzulHotel
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card de Dirección
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Dirección:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = hotelDireccion,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card de Contacto
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = " Contacto:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Teléfono: $hotelTelefono", fontSize = 14.sp)
                    Text("Email: $hotelEmail", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón: Abrir en Google Maps
            Button(
                onClick = {
                    val gmmIntentUri = Uri.parse("geo:$hotelLat,$hotelLng?q=$hotelLat,$hotelLng($hotelNombre)")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulHotel
                )
            ) {
                Text("Abrir en Google Maps", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón: Cómo llegar
            Button(
                onClick = {
                    val navigationUri = Uri.parse("google.navigation:q=$hotelLat,$hotelLng")
                    val mapIntent = Intent(Intent.ACTION_VIEW, navigationUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulHotel
                )
            ) {
                Text("¿Cómo llegar?", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón: Compartir ubicación
            OutlinedButton(
                onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT,
                            "$hotelNombre\n$hotelDireccion\nVer en mapa: https://maps.google.com/?q=$hotelLat,$hotelLng")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Compartir ubicación"))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Compartir Ubicación", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón: Llamar al hotel
            OutlinedButton(
                onClick = {
                    val callIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:$hotelTelefono")
                    }
                    context.startActivity(callIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(" Llamar al Hotel", fontSize = 16.sp)
            }
        }
    }
}