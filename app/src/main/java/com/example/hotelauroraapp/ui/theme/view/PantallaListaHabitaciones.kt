package com.example.hotelauroraapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelauroraapp.model.Habitacion
import com.example.hotelauroraapp.R
import com.example.hotelauroraapp.ui.theme.AzulHotel

@Composable
fun PantallaListaHabitaciones(navController: NavController) {
    val habitaciones = listOf(
        Habitacion(1, "Habitación Estándar Individual", R.drawable.habitacionestan1),
        Habitacion(2, "Habitación Estándar Doble", R.drawable.habitaciondoble1),
        Habitacion(3, "Habitación Familiar", R.drawable.habitacionfami2),
        Habitacion(4, "Habitación Ejecutiva", R.drawable.habitacioneje1),
        Habitacion(5, "Suite Junior", R.drawable.habitacionsui3)
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 30.dp)
        ) {
            // Título centrado
            Text(
                text = "Tipo de habitaciones",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = AzulHotel,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(habitaciones) { habitacion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("detalle/${habitacion.id}")
                            }
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = habitacion.imagenRes),
                            contentDescription = habitacion.nombre,
                            modifier = Modifier.size(100.dp)
                        )
                        Text(
                            text = habitacion.nombre,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.alignByBaseline()
                        )
                    }
                }
            }
        }
    }
}
