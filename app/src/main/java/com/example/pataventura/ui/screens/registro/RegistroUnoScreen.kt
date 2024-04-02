package com.example.pataventura.ui.screens.registro

import Constans
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.InformationButton
import com.example.pataventura.ui.screens.registro.composables.BodyRegistroUno
import com.example.pataventura.ui.screens.registro.composables.HeaderRegistro

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroUnoScreen(
    navController: NavController,
    registroViewModel: RegistroViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderRegistro()
            BodyRegistroUno(registroViewModel = registroViewModel, navController = navController)

        }
    }
}



