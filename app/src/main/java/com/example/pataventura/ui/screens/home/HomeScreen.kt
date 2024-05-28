package com.example.pataventura.ui.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.composables.navegacionButtonBar
import com.example.pataventura.ui.screens.home.composables.BodyHome
import com.example.pataventura.ui.screens.home.composables.HeaderHome
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState


@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    val currentLoc by homeViewModel.currentLocTutor.observeAsState()
    val listaCuidadores by homeViewModel.listaCuidadores.observeAsState(emptyList())
    val listaCuidadoresPaseo by homeViewModel.listaCuidadoresPaseo.observeAsState(emptyList())
    val listaCuidadoresGuarderia by homeViewModel.listaCuidadoresGuarderia.observeAsState(emptyList())
    val listaMascotas: List<Mascota> by homeViewModel.mascotas.observeAsState(emptyList())

    LaunchedEffect(key1 = Any()) {
        homeViewModel.setNombre()
        homeViewModel.getMascotas()
    }

    var selectedIcon by remember { mutableStateOf(Icons.Default.Home) }
    Scaffold(bottomBar = {
        BackHandler {}
        BottomBar(selectedIcon, navController) { icon ->
            selectedIcon = icon
            navegacionButtonBar(icon, navController, RoleHolder.rol.value.toString().lowercase())
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            HeaderHome(homeViewModel)
            BodyHome(currentLoc, homeViewModel, listaMascotas, listaCuidadores, navController,
                listaCuidadoresPaseo, listaCuidadoresGuarderia)
        }
    }

}

private suspend fun CameraPositionState.centerOnLocation(
    location: LatLng
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        15f
    ),
    durationMs = 1500
)