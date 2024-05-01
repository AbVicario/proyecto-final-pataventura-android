package com.example.pataventura.ui.screens.contratacion

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
import com.example.pataventura.ui.screens.contratacion.composables.BodyContratacion

import com.example.pataventura.ui.screens.contratacion.composables.HeaderContratacion


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContratacionScreen(
    navController: NavController,
    contratacionViewModel: ContratacionViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderContratacion()
            Spacer(modifier = Modifier.size(10.dp))
            BodyContratacion()

        }
    }

}