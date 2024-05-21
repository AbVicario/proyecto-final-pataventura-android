package com.example.pataventura.ui.screens.perfil_mascota

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.perfil_mascota.composables.BodyPerfilMascotas
import com.example.pataventura.ui.screens.perfil_mascota.composables.HeaderPerfilMascota
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilMascotaScreen(
    navController: NavController,
    perfilMascotaViewModel: PerfilMascotaViewModel
) {
    val editMode by perfilMascotaViewModel.editMode.observeAsState(false)

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderPerfilMascota(perfilMascotaViewModel, editMode)
            Spacer(modifier = Modifier.size(10.dp))
            BodyPerfilMascotas(perfilMascotaViewModel = perfilMascotaViewModel,
                navController = navController, editMode
            )
        }
    }
}