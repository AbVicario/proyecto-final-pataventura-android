package com.example.pataventura.ui.screens.perfil_trabajador.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.contratacion.composables.MyCardPerfil
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyPerfil(){
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

            MyCardPerfil()

            LazyColumn(Modifier.padding(25.dp)){
                item {
                    MyCardValoraciones()
                    MyCardValoraciones()
                    MyCardValoraciones()
                    MyCardValoraciones()
                    MyCardValoraciones()
                    MyCardValoraciones()
                    MyCardValoraciones()
                }
            }


        }
    }
}

@Composable
fun MyCardValoraciones() {
    Card (modifier = Modifier.padding(bottom = 15.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(),
        border = CardDefaults.outlinedCardBorder()
    ){
            Column(Modifier.padding(15.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                ) {

                CustomText(text = "Nombre Tutor", color = Color.Black, fontSize = 20.sp,
                    fontWeight = FontWeight.Bold , fontFamily = CustomFontFamily
                )

                Row (Modifier.padding()){
                    Icon(
                        Icons.Default.StarRate, contentDescription = null,
                        Modifier.size(25.dp),tint = Verde
                    )
                    Icon(
                        Icons.Default.StarRate, contentDescription = null,
                        Modifier.size(25.dp), tint = Verde
                    )
                    Icon(
                        Icons.Default.StarRate, contentDescription = null,
                        Modifier.size(25.dp),tint = Verde
                    )
                    Icon(
                        Icons.Default.StarHalf, contentDescription = null,
                        Modifier.size(25.dp), tint = Verde
                    )
                    Icon(
                        Icons.Default.StarOutline, contentDescription = null,
                        Modifier.size(25.dp), tint = Verde
                    )
                }

                CustomText(text = "Valoración del cuidador", color = Color.Black, fontSize = 16.sp,
                    fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
                )


            }

    }

}