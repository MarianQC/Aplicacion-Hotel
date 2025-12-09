package com.example.hotelauroraapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título del hotel
            item {
                Text(
                    text = "Hotel Aurora",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulHotel,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 20.dp)
                )
            }

            // Imagen del hotel
            item {
                Image(
                    painter = painterResource(id = R.drawable.fachadahotel2),
                    contentDescription = "Foto del Hotel Aurora",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Descripción del hotel
            item {
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
            }

            // Botón para ir a la siguiente pantalla
            item {
                Button(
                    onClick = { navController.navigate("rooms") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(70.dp)
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
}
