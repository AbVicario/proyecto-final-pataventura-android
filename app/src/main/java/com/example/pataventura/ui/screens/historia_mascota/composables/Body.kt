package com.example.pataventura.ui.screens.historia_mascota.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CardHistorial
import com.example.pataventura.ui.composables.RowHistorial


@Composable
fun BodyHistorialMascota(){
    var nombreMascota = "Tyrion"
    var nombreCuidador = "Pepe"
    var fechaInicio = "12/04/2024"
    var fehaFin = "14/04/2024"
    var precio = "15€"
    var imageMascota = R.drawable.imagen_boton_perro
    var imageCuidador = R.drawable.imagen_perfil
    var servicio = "Guardería"


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

            RowHistorial(text = nombreMascota, image = imageMascota)
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn(Modifier.padding(horizontal = 10.dp)) {
                item {
                    repeat(7) {
                        CardHistorial(
                            nombreMascota, nombreCuidador, fechaInicio, fehaFin, precio,
                            imageMascota, imageCuidador, servicio, true
                        )
                        Spacer(modifier = Modifier.size(30.dp))
                    }

                }
            }


        }
    }
}
