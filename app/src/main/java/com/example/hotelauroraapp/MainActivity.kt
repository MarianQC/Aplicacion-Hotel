package com.example.hotelauroraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotelauroraapp.ui.theme.view.PantallaInicial
import com.example.hotelauroraapp.ui.view.PantallaInformacionInicial
import com.example.hotelauroraapp.ui.view.PantallaListaHabitaciones
import com.example.hotelauroraapp.ui.view.PantallaDetalleHabitacion
import androidx.navigation.NavType
import androidx.navigation.navArgument
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotelAuroraApp()
            }
        }
    }
@Composable
fun HotelAuroraApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Inicio") {
        composable("Inicio") { PantallaInicial(navController) }
      //  composable("login") { LoginScreen(navController) }
        composable("info") { PantallaInformacionInicial(navController) }
        composable("rooms") { PantallaListaHabitaciones(navController) }
        composable(
            route = "detalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            PantallaDetalleHabitacion(navController, id)
        }
    }
}





