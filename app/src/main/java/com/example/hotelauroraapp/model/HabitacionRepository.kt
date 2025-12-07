package com.example.hotelauroraapp.model

import com.example.hotelauroraapp.R

// Data class extendida solo para las pantallas de detalle
data class HabitacionDetalle(
    val id: Int,
    val nombre: String,
    val imagenRes: Int,
    val descripcion: String,
    val amenidades: String,
    val capacidad: String,
    val precio: String
)

// Función para obtener todas las habitaciones con información completa
fun obtenerHabitacionesDetalle(): List<HabitacionDetalle> {
    return listOf(
        HabitacionDetalle(
            id = 1,
            nombre = "Habitación Estándar Individual",
            imagenRes = R.drawable.habitacionestan1,
            descripcion = "Es la habitación ideal para quienes les gusta viajar.",
            amenidades = "Desayuno incluido, parqueadero reservado, servicio a la habitación, cuna adicional, check-out extendido, servicio de lavandería exprés, traslado al aeropuerto, cama individual, aire acondicionado, wi-fi, tv por cable, baño privado con ducha.",
            capacidad = "1 persona",
            precio = "$30 por noche"
        ),
        HabitacionDetalle(
            id = 2,
            nombre = "Habitación Estándar Doble",
            imagenRes = R.drawable.habitaciondoble1,
            descripcion = "Ideal para parejas, hermanos o compañeros de trabajo.",
            amenidades = "Desayuno incluido, parqueadero reservado, servicio a la habitación, cuna adicional, check-out extendido, servicio de lavandería exprés, traslado al aeropuerto, 1 cama matrimonial o 2 camas individuales, aire acondicionado, wi-fi, tv por cable, baño privado, escritorio.",
            capacidad = "2 personas",
            precio = "$40 por noche"
        ),
        HabitacionDetalle(
            id = 3,
            nombre = "Habitación Familiar",
            imagenRes = R.drawable.habitacionfami2,
            descripcion = "Ideal para familias pequeñas o grupos de amigos.",
            amenidades = "Desayuno incluido, parqueadero reservado, servicio a la habitación, cuna adicional, check-out extendido, servicio de lavandería exprés, traslado al aeropuerto, 1 cama matrimonial + 2 camas individuales, aire acondicionado, wi-fi, tv pantalla plana, mini refrigeradora, baño privado, armario amplio.",
            capacidad = "4 personas",
            precio = "$60 por noche"
        ),
        HabitacionDetalle(
            id = 4,
            nombre = "Habitación Ejecutiva",
            imagenRes = R.drawable.habitacioneje1,
            descripcion = "Ideal para viajes de negocios.",
            amenidades = "Desayuno incluido, parqueadero reservado, servicio a la habitación, cuna adicional, check-out extendido, servicio de lavandería exprés, traslado al aeropuerto, cama queen, escritorio grande, aire acondicionado, wi-fi rápido, minibar, caja fuerte, tv smart, baño privado con ducha moderna.",
            capacidad = "2 personas",
            precio = "$55 por noche"
        ),
        HabitacionDetalle(
            id = 5,
            nombre = "Suite Junior",
            imagenRes = R.drawable.habitacionsui3,
            descripcion = "Ideal para las personas que aman el lujo y confort.",
            amenidades = "Desayuno incluido, parqueadero reservado, servicio a la habitación, cuna adicional, check-out extendido, servicio de lavandería exprés, traslado al aeropuerto, cama king, sala pequeña, vista a la ciudad, bañera, aire acondicionado, wi-fi premium, cafetera, tv de 50\".",
            capacidad = "2 personas",
            precio = "$85 por noche"
        )
    )
}