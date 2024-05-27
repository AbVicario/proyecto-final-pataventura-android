package com.example.pataventura.ui.screens.servicio

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.composables.navegacionButtonBar
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel
import com.example.pataventura.ui.screens.servicio.composables.BodyServicio
import com.example.pataventura.ui.screens.servicio.composables.HeaderServicio


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ServicioScreen(
    navController: NavController,
    servicioViewModel: ServicioViewModel,
    perfilTrabajadorViewModel: PerfilTrabajadorViewModel
) {

    var selectedIcon by remember { mutableStateOf(Icons.Default.Badge) }
    val cuidador by perfilTrabajadorViewModel.cuidador.observeAsState()

    Scaffold(
        bottomBar = {
            BottomBar(selectedIcon, navController) { icon ->
                selectedIcon = icon
                navegacionButtonBar(icon, navController, RoleHolder.rol.value.toString().lowercase())
            }
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LaunchedEffect(Unit) {
                servicioViewModel.pintarServicios()
            }
            HeaderServicio(ImageConverter.byteArrayToImageBitmap(cuidador!!.imagen))
            Spacer(modifier = Modifier.height(16.dp))
            BodyServicio(servicioViewModel, navController)
        }
    }
}