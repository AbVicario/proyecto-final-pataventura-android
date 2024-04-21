package com.example.pataventura.ui.screens.mascotas.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.home.composables.MyBoxMap
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.google.android.gms.maps.model.LatLng

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BodyMascotas(currentPosition: LatLng?){
    Box(modifier = Modifier.fillMaxWidth()){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize())
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
            ){
                MyBoxMap(currentPosition)

            }

            LazyRow(Modifier.height(120.dp)) {
                item{
                    MyBoxMascota(imagen = R.drawable.imagen_tyrion, nombre = "Lola", Color.Red)
                    Spacer(modifier = Modifier.size(20.dp))
                    MyBoxMascota(imagen = R.drawable.imagen_tyrion, nombre = "Lola", Color.Red)
                    Spacer(modifier = Modifier.size(20.dp))
                    MyBoxMascota(imagen = R.drawable.imagen_tyrion, nombre = "Lola", Color.Red)
                    Spacer(modifier = Modifier.size(20.dp))
                    MyBoxMascota(imagen = R.drawable.imagen_tyrion, nombre = "Lola", Color.Red)
                    Spacer(modifier = Modifier.size(20.dp))
                    MyBoxMascota(imagen = R.drawable.imagen_tyrion, nombre = "Lola", Color.Red)

                }

            }
        }
    }

}

@Composable
fun MyBoxMascota(imagen: Int, nombre: String, color: Color) {
    Box(modifier = Modifier
        .size(180.dp)
        .clip(RoundedCornerShape(20.dp))){
        Image(painter = painterResource(id = imagen), contentDescription = "Imagen mascota",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(Verde, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(horizontal = 15.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier = Modifier
                    .size(25.dp)
                    .background(color, RoundedCornerShape(100f))
                )
                CustomText(text = nombre, color = Color.White,
                    fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
            }
    }

}





