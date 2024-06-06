package com.example.pataventura.ui.screens.calendario

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.composables.navegacionButtonBar
import com.example.pataventura.ui.screens.calendario.composables.BodyCalendario

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarioScreen(
    navController: NavController,
    calendarioModel: CalendarioViewModel
) {
    LaunchedEffect(Unit) {
        calendarioModel.getDemandasAceptadas()
    }
    var selectedIcon by remember { mutableStateOf(Icons.Default.CalendarMonth) }
    Scaffold( bottomBar = {
        BottomBar(selectedIcon, navController) { icon ->
            selectedIcon = icon
            navegacionButtonBar(icon, navController, RoleHolder.rol.value.toString().lowercase())
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween,
        ) {
            //HeaderCalendario()
            BodyCalendario(calendarioModel)
        }
    }

}