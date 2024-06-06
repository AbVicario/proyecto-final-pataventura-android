package com.example.pataventura.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun CardHistorial(
    nombreMascota: String, nombreCuidador: String,
    fechaInicio: String, fechaFin: String, precio: String,
    imageMascota: Int, imageCuidador: Int, servicio: String,
    mascota: Boolean, historialMascotaViewModel: HistorialMascotaViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(165.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(0.5f)
        ),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Verde)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mascota) {
                    Image(
                        painter = painterResource(id = imageCuidador),
                        contentDescription = null,
                        Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(100f))
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                    RowValoracion(25)

                } else {
                    Image(
                        painter = painterResource(id = imageMascota),
                        contentDescription = null,
                        Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(100f))
                    )
                }

            }
            Spacer(modifier = Modifier.size(15.dp))
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(300.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .width(120.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        CustomText(
                            text = servicio,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                        Spacer(modifier = Modifier.size(8.dp))

                        CustomText(
                            text = if (mascota) nombreCuidador else nombreMascota,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                    }

                    if (mascota) {
                        Box() {
                            MyCustomButton(texto = "Valorar", color = Verde) {
                                historialMascotaViewModel.showDialog()
                            }
                            ValoracionesScreen(historialMascotaViewModel)
                        }
                    }

                }


                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = "Fecha inicio: $fechaInicio",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                    CustomText(
                        text = "Fecha fin: $fechaFin",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    CustomText(
                        text = "Precio: $precio",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                }
            }

        }

    }
}
