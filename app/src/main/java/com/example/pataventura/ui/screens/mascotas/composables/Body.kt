package com.example.pataventura.ui.screens.mascotas.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.home.composables.MyBoxMap
import com.example.pataventura.ui.screens.mascotas.MascotasViewModel
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Tierra
import com.example.pataventura.ui.theme.Verde
import com.google.android.gms.maps.model.LatLng

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BodyMascotas(
    currentPosition: LatLng?,
    mascotasViewModel: MascotasViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        mascotasViewModel.getMascotas()
    }

    val listaMascotas by mascotasViewModel.mascotas.observeAsState(emptyList())
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                MyBoxMap(currentPosition)

            }

            LazyRow(
                Modifier.height(180.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items(listaMascotas.size) { index ->
                    listaMascotas.getOrNull(index).let { mascota ->
                        if (mascota != null) {
                            MyBoxMascota(
                                mascota,
                                navController
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }
                    if (listaMascotas.size - 1 == index) {
                        MyBoxAnyadirMascota(navController)
                    }
                }
                item {
                    if (listaMascotas.isEmpty()) {
                        MyBoxAnyadirMascota(navController)
                    }
                }

            }
        }
    }

}

@Composable
fun MyBoxAnyadirMascota(navController: NavController) {
    Box(modifier = Modifier
        .size(70.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Tierra)
        .padding(5.dp)
        .clickable { navController.navigate("registerMascota") }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Añadir mascota",
            modifier = Modifier.fillMaxSize(),
            tint = Color.White
        )
    }
}

@Composable
fun MyBoxMascota(mascota:Mascota, navController: NavController) {
    Box(
        modifier = Modifier
            .size(180.dp)
            .background(Tierra, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate("perfilMascota/"+mascota.idMascota)
            }
    ) {
        if (mascota.imagen!!.isEmpty()) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "Icono mascota",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(ImageConverter.byteArrayToImageBitmap(mascota.imagen!!), contentDescription = "Imagen mascota",
                Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(Verde, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(horizontal = 15.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .background(obtenerColor(mascota.color), RoundedCornerShape(100f))
            )
            CustomText(
                text = mascota.nombre, color = Color.White,
                fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
        }
    }

}

fun obtenerColor(color: String): Color {
    when (color.lowercase()) {
        "amarillo" -> return Color.Yellow
        "rojo" -> return Color.Red
        "azul" -> return Color.Blue
        "verde" -> return Color.Green
        "negro" -> return Color.Black
        else -> return Color.White
    }
}





