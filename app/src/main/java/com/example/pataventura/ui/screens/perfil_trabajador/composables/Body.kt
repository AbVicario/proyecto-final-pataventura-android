package com.example.pataventura.ui.screens.perfil_trabajador.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.MyValoracionStars
import com.example.pataventura.ui.screens.contratacion.composables.MyCardPerfil
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyPerfil(perfilTrabajadorViewModel: PerfilTrabajadorViewModel){

    val listValoraciones by perfilTrabajadorViewModel.valoraciones.observeAsState()
    val cuidador by perfilTrabajadorViewModel.cuidador.observeAsState(Cuidador())

    Box(){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize())

        Column(
            Modifier
                .fillMaxWidth()
                .height(700.dp)
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            MyCardPerfil(cuidador, listValoraciones!!)

            LazyColumn(Modifier.padding(25.dp)){
                items(listValoraciones!!.size) { index ->
                    listValoraciones!!.getOrNull(index).let { valoracion ->
                        if (valoracion != null) {
                            MyCardValoraciones(valoracion)
                            Spacer(modifier = Modifier.size(15.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyCardValoraciones(valoracion: Valoracion) {
    Card (modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(1.dp, Verde)
    ){
        Row(Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.padding(15.dp).width(100.dp).height(130.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box ( modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .size(85.dp)
                    .clip(RoundedCornerShape(100f))
                    .background(Verde.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(ImageConverter.byteArrayToImageBitmap(valoracion.imagenTutor),
                        contentDescription = "Imagen tutor",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                CustomText(text = valoracion.aliasTutor, color = Color.Black, fontSize = 20.sp,
                    fontWeight = FontWeight.Bold , fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start) {
                MyValoracionStars(valoracion = valoracion.puntuacion, sizeStars = 25)
                Spacer(modifier = Modifier.size(10.dp))
                CustomText(text = valoracion.descripcion, color = Color.Black, fontSize = 16.sp,
                    fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
                )
            }
        }

    }

}