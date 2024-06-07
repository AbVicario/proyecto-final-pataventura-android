package com.example.pataventura.ui.screens.historia_cuidador.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CardHistorialCuidador
import com.example.pataventura.ui.composables.RowHistorial
import com.example.pataventura.ui.screens.historia_mascota.HistorialCuidadorViewModel
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaViewModel
@Composable
fun BodyHistorialCuidador(
    navController: NavController,
    historialCuidadorViewModel: HistorialCuidadorViewModel,
    historialMascotaViewModel: HistorialMascotaViewModel
) {
    val emptyImage = ImageBitmap(1,1)
    val nombreCuidador by historialCuidadorViewModel.nombre.observeAsState("")
    val imageCuidador by historialCuidadorViewModel.imagen.observeAsState(emptyImage)
    val demandasRealizadas by historialCuidadorViewModel.demandasRealizadas.observeAsState(emptyList())

    Box() {
        Image(
            painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier
                .align(Alignment.TopStart)
                .fillMaxSize()
                .padding(top = 300.dp)
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RowHistorial(text = nombreCuidador, imageCuidador!!)
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn(Modifier.padding(horizontal = 10.dp)) {
                items(demandasRealizadas.size) { index ->
                    demandasRealizadas.getOrNull(index).let { demanda ->
                        if (demanda != null) {
                            CardHistorialCuidador(
                                navController,
                                demanda,
                                false,
                                historialCuidadorViewModel,
                                historialMascotaViewModel
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
        }
    }
}
