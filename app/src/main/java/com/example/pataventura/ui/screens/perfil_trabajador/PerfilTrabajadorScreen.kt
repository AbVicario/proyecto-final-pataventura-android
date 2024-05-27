package com.example.pataventura.ui.screens.perfil_trabajador

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.composables.navegacionButtonBar
import com.example.pataventura.ui.screens.contratacion.ContratacionViewModel
import com.example.pataventura.ui.screens.contratacion.composables.BodyContratacion
import com.example.pataventura.ui.screens.contratacion.composables.HeaderContratacion
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.composables.BodyPerfil
import com.example.pataventura.ui.screens.perfil_trabajador.composables.HeaderPerfil

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilTrabajadorScreen(
    navController: NavController,
    perfilTrabajadorViewModel: PerfilTrabajadorViewModel,
    homeViewModel: HomeViewModel,
) {
    val rol = RoleHolder.rol.value.toString().lowercase()

    LaunchedEffect(key1 = Any()) {
        perfilTrabajadorViewModel.setCuidador(homeViewModel.listaCuidadores.value!!)
        /*  ESTA LINEA NO ES PARA BORRAR, ES PARA DECOMENTAR CUANDO LA APP ESTÉ
        * PROBADA YA QUE AHORA MISMO EL CUIDADOR Y EL TUTOR TIENEN LA MISMA
        * UBICACIÓN Y PARA LAS PRUEBAS SE SUPERPONDRÍAN EN EL MAPA*/
        //perfilTrabajadorViewModel.handle()
    }

    var selectedIcon by remember { mutableStateOf(Icons.Default.Home) }
    Scaffold(
        bottomBar = {
            if (rol == "cuidador") {
                BackHandler {}
                BottomBar(selectedIcon, navController) {
                    selectedIcon = it
                    navegacionButtonBar(it, navController, rol)
                }
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
            BodyPerfil(perfilTrabajadorViewModel)

        }
    }
}


