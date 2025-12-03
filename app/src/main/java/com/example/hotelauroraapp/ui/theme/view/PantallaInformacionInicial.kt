package com.example.hotelauroraapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.hotelauroraapp.R
import com.example.hotelauroraapp.ui.theme.AzulHotel

@Composable
fun PantallaInformacionInicial(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título del hotel
            Text(
                text = "Hotel Aurora",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = AzulHotel, // mismo color que tu fondo principal
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp,
                        bottom = 20.dp,
                    )
            )

            // Imagen del hotel (solo un cuarto de la pantalla)
            Image(
                painter = painterResource(id = R.drawable.fachadahotel2),
                contentDescription = "Foto del Hotel Aurora",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp), // altura fija
                contentScale = ContentScale.Crop
            )


            // Descripción del hotel
            Text(
                text = "El Hotel Aurora ofrece una experiencia única en Guayaquil, " +
                        "combinando comodidad, elegancia y un servicio excepcional. Disfruta " +
                        "de nuestras habitaciones modernas, áreas sociales y la mejor ubicación " +
                        "en el corazón de la ciudad.",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )

            // Botón para ir a la siguiente pantalla (más grande)
            Button(
                onClick = { navController.navigate("rooms") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(70.dp) // más alto para que se vea grande
            ) {
                Text(
                    "Siguiente",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

