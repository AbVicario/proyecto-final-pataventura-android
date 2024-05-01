package com.example.pataventura.ui.screens.mascotas

import HandleLocationPermissionAndState
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.mascotas.composables.BodyMascotas
import com.example.pataventura.ui.screens.mascotas.composables.HeaderMascotas

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MascotasScreen(
    navController: NavController,
    mascotasViewModel: MascotasViewModel,
    homeViewModel: HomeViewModel
){
    var selectedIcon by remember { mutableStateOf(Icons.Default.Pets) }
    val currentLoc by homeViewModel.currentLoc.observeAsState()
    Scaffold( bottomBar = {
    BottomBar(selectedIcon){
        selectedIcon = it
    }
}) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderMascotas()
            Spacer(modifier = Modifier.size(10.dp))
            BodyMascotas(currentLoc)
            HandleLocationPermissionAndState(homeViewModel)
        }
    }

}

