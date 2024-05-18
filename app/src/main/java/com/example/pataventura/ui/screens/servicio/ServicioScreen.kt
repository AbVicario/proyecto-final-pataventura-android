package com.example.pataventura.ui.screens.servicio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.servicio.composables.BodyServicio
import com.example.pataventura.ui.screens.servicio.composables.HeaderServicio


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ServicioScreen(
    navController: NavController,
    servicioViewModel: ServicioViewModel
) {
    val image: ImageBitmap? = null
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LaunchedEffect(Unit) {
                servicioViewModel.pintarServicios()
            }
            HeaderServicio(image)
            Spacer(modifier = Modifier.height(16.dp))
            BodyServicio(servicioViewModel, navController)
        }
    }
}