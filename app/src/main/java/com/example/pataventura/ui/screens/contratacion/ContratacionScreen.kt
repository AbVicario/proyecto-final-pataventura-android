package com.example.pataventura.ui.screens.contratacion

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.contratacion.composables.BodyContratacion

import com.example.pataventura.ui.screens.contratacion.composables.HeaderContratacion
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel


@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContratacionScreen(
    navController: NavController,
    contratacionViewModel: ContratacionViewModel,
    homeViewModel: HomeViewModel
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderContratacion()
            Spacer(modifier = Modifier.size(10.dp))
            BodyContratacion(contratacionViewModel, navController, homeViewModel)

        }
    }

}