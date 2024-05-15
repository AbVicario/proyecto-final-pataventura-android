package com.example.pataventura.ui.screens.registro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.registro.composables.BodyRegistroDos
import com.example.pataventura.ui.screens.registro.composables.HeaderRegistro

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroDosScreen(
    navController: NavController,
    registroViewModel: RegistroViewModel,
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderRegistro()
            BodyRegistroDos(registroViewModel = registroViewModel,
                navController)

        }
    }
}