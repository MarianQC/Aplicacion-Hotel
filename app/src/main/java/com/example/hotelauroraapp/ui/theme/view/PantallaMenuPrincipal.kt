package com.example.hotelauroraapp.ui.theme.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PantallaMenuPrincipal(navController: NavHostController){ // <-- El nombre de la función es clave
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Bienvenido a Hotel Aurora",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))


            // BOTÓN DE UBICACIÓN
            Button(
                onClick = { navController.navigate("ubicacion") }, // Navega a GeolocationScreen
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
            ) {
                Text("🗺️ Ubicación y Contacto")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para Información
            Button(
                onClick = { navController.navigate("info") },
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
            ) {
                Text("Información del Hotel")
            }
        }
    }
}