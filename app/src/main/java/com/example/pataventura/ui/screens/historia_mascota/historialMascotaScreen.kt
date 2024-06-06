package com.example.pataventura.ui.screens.historia_mascota

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.historia_mascota.composables.BodyHistorialMascota
import com.example.pataventura.ui.screens.historia_mascota.composables.HeaderHistorialMascota

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistorialMascotaScreen(
    navController: NavController,
    historialMascotaViewModel: HistorialMascotaViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderHistorialMascota()
            Spacer(modifier = Modifier.size(10.dp))
            BodyHistorialMascota(navController, historialMascotaViewModel)

        }
    }

}