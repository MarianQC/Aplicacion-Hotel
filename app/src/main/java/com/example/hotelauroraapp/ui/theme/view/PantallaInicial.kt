package com.example.hotelauroraapp.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hotelauroraapp.R
import com.example.hotelauroraapp.ui.theme.AzulHotel
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay

@Composable
fun PantallaInicial(navController: NavHostController) {

    // Efecto que se ejecuta al entrar en la pantalla
    LaunchedEffect(Unit) {
        delay(4000) // espera 4 segundos
        navController.navigate("info") {
            popUpTo("Inicio") { inclusive = true }
            // elimina la pantalla inicial de la pila para que no se pueda volver atrás
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulHotel), // fondo azul
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoultimo),
            contentDescription = "Logo del Hotel",
            modifier = Modifier.size(300.dp)
        )
    }
}