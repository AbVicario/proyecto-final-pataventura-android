package com.example.pataventura.ui.screens.registoMascota

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.registoMascota.composables.BodyRegistroMascota
import com.example.pataventura.ui.screens.registoMascota.composables.HeaderRegistroMascota

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroMascotaScreen(
    navController: NavController,
    registroMascotaViewModel: RegistroMascotaViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderRegistroMascota()
            BodyRegistroMascota(registroMascotaViewModel = registroMascotaViewModel,navController)
        }
    }
}