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
import com.example.pataventura.ui.screens.historia_cuidador.composables.HeaderHistorialCuidador
import com.example.pataventura.ui.screens.historia_cuidador.composables.BodyHistorialCuidador

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistorialCuidadorScreen(
    navController: NavController,
    historialCuidadorViewModel: HistorialCuidadorViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderHistorialCuidador()
            Spacer(modifier = Modifier.size(10.dp))
            BodyHistorialCuidador()

        }
    }

}