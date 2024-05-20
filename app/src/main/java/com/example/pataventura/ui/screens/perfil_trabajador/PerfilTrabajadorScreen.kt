package com.example.pataventura.ui.screens.perfil_trabajador

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.screens.contratacion.ContratacionViewModel
import com.example.pataventura.ui.screens.contratacion.composables.BodyContratacion
import com.example.pataventura.ui.screens.contratacion.composables.HeaderContratacion
import com.example.pataventura.ui.screens.perfil_trabajador.composables.BodyPerfil
import com.example.pataventura.ui.screens.perfil_trabajador.composables.HeaderPerfil

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilTrabajadorScreen(
    navController: NavController,
    perfilTrabajadorViewModel: PerfilTrabajadorViewModel
) {
    var selectedIcon by remember { mutableStateOf(Icons.Default.Home) }
    Scaffold(
        bottomBar = {
            BackHandler {}
            BottomBar(selectedIcon, navController) {
                selectedIcon = it
            }
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderPerfil()
            Spacer(modifier = Modifier.size(10.dp))
            BodyPerfil()

        }
    }
}


