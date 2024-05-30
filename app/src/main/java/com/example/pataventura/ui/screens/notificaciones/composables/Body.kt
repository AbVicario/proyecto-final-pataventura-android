package com.example.pataventura.ui.screens.notificaciones.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Notificacion
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.screens.notificaciones.NotificacionesViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun BodyNotificaciones(
    notificacionesViewModel: NotificacionesViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val notificaciones: List<Notificacion> by loginViewModel.notificaciones.observeAsState(
        emptyList()
    )
    val mascota: Mascota by notificacionesViewModel.mascota.observeAsState(Mascota())


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

            CustomText(
                text = "Notificaciones:",
                color = Verde,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CustomFontFamily
            )
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn(
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 50.dp)) {
                items(notificaciones.size) { index ->
                    notificaciones.getOrNull(index).let { notificacion ->
                        if (notificacion != null) {
                            MyCardNotificacion(
                                notificacion,
                                mascota,
                                navController,
                                notificacionesViewModel,
                                loginViewModel
                            )
                            Spacer(modifier = Modifier.size(35.dp))
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun MyCardNotificacion(
    notificacion: Notificacion,
    mascota: Mascota,
    navController: NavController,
    notificacionesViewModel: NotificacionesViewModel,
    loginViewModel: LoginViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(0.5f)
        ),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Verde)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                if (notificacion.estado.lowercase() == "nueva") {
                    CustomText(
                        text = notificacion.estado.capitalize(),
                        color = Verde,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = CustomFontFamily
                    )
                } else {
                    CustomText(
                        text = notificacion.estado.capitalize(),
                        color = Verde,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = CustomFontFamily
                    )
                }
                CustomText(
                    text = notificacion.fechaCreacion,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            CustomText(
                text = notificacion.descripcion,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = CustomFontFamily
            )
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {

                CustomText(
                    text = notificacion.tipo.capitalize() + ": ",
                    color = Verde,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
                CustomText(
                    text = notificacion.mascotaName,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row() {
                CustomText(
                    text = "Precio total: ",
                    color = Verde,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
                CustomText(
                    text = notificacion.demanda.precio.toString() + "€",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            format.timeZone = java.util.TimeZone.getTimeZone("UTC")
            val dateInicio: Date = format.parse(notificacion.demanda.fechaInicio)
            val dateFin: Date = format.parse(notificacion.demanda.fechaFin)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val outputDateStringInicio: String = outputFormat.format(dateInicio)
            val outputDateStringFin: String = outputFormat.format(dateFin)
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {

                CustomText(
                    text = "Fecha inicio: ",
                    color = Verde,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
                CustomText(
                    text = outputDateStringInicio,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                CustomText(
                    text = "Fecha fin: ",
                    color = Verde,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
                CustomText(
                    text = outputDateStringFin,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                CustomText(
                    text = "Dirección: ",
                    color = Verde,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
                CustomText(
                    text = notificacion.direccion,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            if (notificacion.estado.lowercase() == "nueva" && RoleHolder.rol.value.toString()
                    .lowercase() == "cuidador"
            ) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    MyCustomButton(texto = "Aceptar", color = Verde) {
                        notificacion.estado = "leida"
                        notificacionesViewModel.updateNotificacion(notificacion)
                        val demanda = notificacion.demanda
                        demanda.estado = "Aceptada"
                        notificacionesViewModel.updateDemanda(demanda)
                        notificacionesViewModel.actualizarLista(loginViewModel, notificacion)
                        navController.navigate("notificaciones")
                    }
                    MyCustomButton(texto = "Rechazar", color = Color.Red) {
                        notificacion.estado = "leida"
                        notificacionesViewModel.updateNotificacion(notificacion)
                        val demanda = notificacion.demanda
                        demanda.estado = "Rechazada"
                        notificacionesViewModel.updateDemanda(demanda)
                        notificacionesViewModel.actualizarLista(loginViewModel, notificacion)
                        navController.navigate("notificaciones")
                    }
                }

            } else {
                Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.End) {
                    MyCustomButton(texto = "Borrar", color = Color.Red) {
                        notificacion.estado = "Borrada"
                        notificacionesViewModel.updateNotificacion(notificacion)
                        notificacionesViewModel.actualizarLista(loginViewModel, notificacion)
                        navController.navigate("notificaciones")
                    }

                }
            }
        }


    }
}
