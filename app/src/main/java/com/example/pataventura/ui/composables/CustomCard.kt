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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.DemandaAceptada
import com.example.pataventura.ui.screens.contratacion.composables.valoracionMedia
import com.example.pataventura.ui.screens.historia_mascota.HistorialCuidadorViewModel
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun CardHistorial(
    navController: NavController,
    demanda: DemandaAceptada,
    mascota: Boolean,
    historialMascotaViewModel: HistorialMascotaViewModel
) {

    var precioFormateado = ""
    if (demanda.precio.toString().contains(".0")) {
        precioFormateado = demanda.precio.toString().split(".")[0] + "€"
    } else {
        precioFormateado = demanda.precio.toString() + "€"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (mascota) {
                    Image(
                        ImageConverter.byteArrayToImageBitmap(demanda.cuidador.imagen),
                        contentDescription = null,
                        Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(100f))
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                } else {
                    Image(
                        ImageConverter.byteArrayToImageBitmap(demanda.mascota.imagen!!),
                        contentDescription = "",
                        Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(100f)),
                        contentScale = ContentScale.FillBounds
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
                        .fillMaxHeight(),
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
                            text = demanda.oferta.tipo.capitalize(),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                        Spacer(modifier = Modifier.size(8.dp))

                        CustomText(
                            text = if (mascota) demanda.cuidador.nombre
                            else demanda.mascota.nombre,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        val valoracion = valoracionMedia(demanda.cuidador.valoraciones!!)
                        MyValoracionStars(valoracion, 25)
                    }

                    if (mascota && !demanda.isValorada) {
                        Box() {
                            MyCustomButton(texto = "Valorar", color = Verde) {
                                historialMascotaViewModel.showDialog()
                                historialMascotaViewModel.idDemanda.postValue(demanda.idDemanda)
                            }
                            ValoracionesScreen(navController ,historialMascotaViewModel)
                        }
                    }

                }


                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = "Fecha inicio: ${demanda.fechaInicio}",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                    CustomText(
                        text = "Fecha fin: ${demanda.fechaFin}",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    CustomText(
                        text = "Precio: $precioFormateado",
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

@Composable
fun CardHistorialCuidador(
    navController: NavController,
    demanda: DemandaAceptada,
    mascota: Boolean,
    historialCuidadorViewModel: HistorialCuidadorViewModel,
    historialMascotaViewModel: HistorialMascotaViewModel
) {

    var precioFormateado = ""
    if (demanda.precio.toString().contains(".0")) {
        precioFormateado = demanda.precio.toString().split(".")[0] + "€"
    } else {
        precioFormateado = demanda.precio.toString() + "€"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (mascota) {
                    Image(
                        ImageConverter.byteArrayToImageBitmap(demanda.cuidador.imagen),
                        contentDescription = null,
                        Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(100f))
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                } else {
                    Image(
                        ImageConverter.byteArrayToImageBitmap(demanda.mascota.imagen!!),
                        contentDescription = "",
                        Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(100f)),
                        contentScale = ContentScale.FillBounds
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
                        .fillMaxHeight(),
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
                            text = demanda.oferta.tipo.capitalize(),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                        Spacer(modifier = Modifier.size(8.dp))

                        CustomText(
                            text = if (mascota) demanda.cuidador.nombre
                            else demanda.mascota.nombre,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                       /* val valoracion = valoracionMedia(demanda.cuidador.valoraciones!!)
                        MyValoracionStars(valoracion, 25)*/
                    }

                    if (mascota && !demanda.isValorada) {
                        Box() {
                            MyCustomButton(texto = "Valorar", color = Verde) {
                                historialCuidadorViewModel.showDialog()
                                historialCuidadorViewModel.idDemanda.postValue(demanda.idDemanda)
                            }
                            ValoracionesScreen(navController ,historialMascotaViewModel)
                        }
                    }

                }


                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = "Fecha inicio: ${demanda.fechaInicio}",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                    CustomText(
                        text = "Fecha fin: ${demanda.fechaFin}",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    CustomText(
                        text = "Precio: $precioFormateado",
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
