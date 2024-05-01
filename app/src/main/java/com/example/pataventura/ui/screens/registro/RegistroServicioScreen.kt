package com.example.pataventura.ui.screens.registro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.registoMascota.composables.HeaderRegistroMascota
import com.example.pataventura.ui.screens.registro.composables.BodyRegistroServicio
import com.example.pataventura.ui.screens.registro.composables.HeaderRegistro

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroServicioScreen(
    navController: NavController,
    registroServicioViewModel: RegistroServicioViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderRegistroMascota()
            BodyRegistroServicio(registroServicioViewModel = registroServicioViewModel,
                navController = navController)

        }
    }
}