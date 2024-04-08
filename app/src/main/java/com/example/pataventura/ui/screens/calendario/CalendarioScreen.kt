package com.example.pataventura.ui.screens.calendario

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.screens.calendario.composables.BodyCalendario
import com.example.pataventura.ui.screens.calendario.composables.HeaderCalendario
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.home.composables.BodyHome
import com.example.pataventura.ui.screens.home.composables.HeaderHome
import kotlinx.coroutines.selects.select

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarioScreen(
    navController: NavController,
    calendarioModel: CalendarioViewModel
) {
    var selectedIcon by remember { mutableStateOf(Icons.Default.CalendarMonth) }
    Scaffold( bottomBar = {
        BottomBar(selectedIcon){
            selectedIcon = it
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween,
        ) {
            HeaderCalendario()
            BodyCalendario()
        }
    }

}