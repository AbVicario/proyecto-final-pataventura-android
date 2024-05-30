package com.example.pataventura.ui.screens.home.composables

import HandleLocationPermissionAndState
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldDes
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyValoracionStars
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BodyHome(
    currentPosition: LatLng?,
    homeViewModel: HomeViewModel,
    listaMascotas: List<Mascota>,
    navController: NavController,
    listaCuidadoresPaseo: List<Cuidador>,
    listaCuidadoresGuarderia: List<Cuidador>
) {
    val listaServicios = listOf("Guardería", "Paseo")
    val servicio: String by homeViewModel.servicio.observeAsState("")
    var selectedIndex by remember { mutableStateOf(-1) }
    var navegar: Boolean by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        CustomText(
            text = "Selecciona tu mascota",
            color = Verde, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
        LazyRow() {
            items(listaMascotas.size) { index ->
                listaMascotas.getOrNull(index).let { mascota ->
                    if (mascota != null) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(
                                    color = if (index == selectedIndex) Color.LightGray else Color.Gray,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .border(
                                    width = if (index == selectedIndex) 2.dp else 0.dp,
                                    color = if (index == selectedIndex) Verde else Color.Transparent,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .shadow(
                                    elevation = if (index == selectedIndex) 8.dp else 0.dp,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .clickable {
                                    selectedIndex = index
                                    navegar = true
                                    homeViewModel.onIdMascotaChange(mascota.idMascota)
                                }
                        ) {
                            MyBoxMascotaHome(mascota, homeViewModel)
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }

        }
        CustomText(
            text = "Selecciona un servicio",
            color = Verde, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            CustomOutlinedTextFieldDes(
                text = servicio,
                items = listaServicios,
                onValueChange = { homeViewModel.onServicioChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                enabled = true,
                readOnly = true,
                placeholder = "Tipo de servicio",
                supportingText = {},
                singleLine = true
            )
        }



        MyBoxMap(
            currentPosition, navController, homeViewModel,
            listaCuidadoresPaseo, listaCuidadoresGuarderia, servicio, navegar
        )
        HandleLocationPermissionAndState(homeViewModel)

    }
}

@Composable
fun ImageButton(painter: Int) {
    Box(
        Modifier
            .background(Color.Transparent, RoundedCornerShape(100f))
            .size(80.dp)
    ) {
        Image(painter = painterResource(painter),
            contentDescription = "Imagen mascota",
            Modifier
                .fillMaxSize()
                .clickable { })
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyBoxMap(
    currentPosition: LatLng?,
    navController: NavController,
    homeViewModel: HomeViewModel,
    listaCuidadoresPaseo: List<Cuidador>,
    listaCuidadoresGuarderia: List<Cuidador>,
    servicio: String,
    navegar: Boolean
) {
    var marker = LatLng(40.416775, -3.703790)
    var cameraStateAux = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }
    if (currentPosition != null) {
        marker = LatLng(currentPosition.latitude, currentPosition.longitude)
        cameraStateAux = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(currentPosition, 12f)
        }
    }

    val valoraciones: List<Valoracion> by homeViewModel.valoraciones.observeAsState(emptyList())
    var cuidador: Cuidador? = null
    var mostrarTexto by remember { mutableStateOf(false) }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 40.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxHeight(0.9f),
        cameraPositionState = cameraStateAux,
        properties = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.NORMAL,
            isTrafficEnabled = false,
            minZoomPreference = 13f,
        )
    ) {
        val showInfoDialog = remember { mutableStateOf(false) }
        val showCuidadores = remember(servicio, listaCuidadoresPaseo, listaCuidadoresGuarderia) {
            derivedStateOf {
                when (servicio.lowercase()) {
                    "paseo" -> listaCuidadoresPaseo
                    else -> listaCuidadoresGuarderia

                }
            }
        }.value

        for (c in showCuidadores) {
            Marker(
                state = MarkerState(
                    position = LatLng(
                        c.ubicacion?.latitude ?: 0.0,
                        c.ubicacion?.longitude ?: 0.0
                    )
                ),
                title = c.alias,
                snippet = "Esto lo peta",
                draggable = true,
                onClick = {
                    cuidador = c
                    homeViewModel.getValoraciones(c.idUsuario)
                    showInfoDialog.value = true
                    true
                }
            )
        }

        if (showInfoDialog.value && cuidador != null) {
            AlertDialog(
                onDismissRequest = { showInfoDialog.value = false
                                   mostrarTexto = false },
                text = {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(start = 5.dp, top = 10.dp, end = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        Destinations.PerfilTrabajador.route
                                                + "/${cuidador!!.idUsuario}"
                                    )
                                }
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .minimumInteractiveComponentSize()
                                        .size(85.dp)
                                        .clip(RoundedCornerShape(100f))
                                        .background(Verde.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        bitmap = ImageConverter.byteArrayToImageBitmap(cuidador!!.imagen),
                                        contentDescription = "Imagen cuidador",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.FillBounds,
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                MyValoracionStars(valoracionMedia(valoraciones), sizeStars = 20)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(
                                modifier = Modifier.padding(start = 10.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Column {
                                    Text(
                                        text = cuidador!!.alias,
                                        color = Verde,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = CustomFontFamily,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                    Text(
                                        text = if (cuidador!!.servicio?.tipo?.lowercase() == "paseo") "Paseador" else "Guardian",
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = CustomFontFamily,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        CustomText(
                            text = cuidador!!.servicio?.descripcion.orEmpty(),
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = CustomFontFamily
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        val precio = cuidador!!.servicio!!.precio
                        val precioFormateado = if ((precio % 1).toDouble() == 0.0) precio.toInt()
                            .toString() else precio.toString()
                        val text = precioFormateado.plus("€")
                        CustomText(
                            text = text,
                            color = Verde,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = CustomFontFamily
                        )
                        if (mostrarTexto) {
                            Spacer(modifier = Modifier.height(10.dp))
                            CustomText(
                                text = "Debe seleccionar una mascota para" +
                                        "poder contratar un servicio",
                                color = Color.Red,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = CustomFontFamily
                            )
                        }
                    }
                },
                confirmButton = {
                    LoginButton(text = "Contratar", null, null) {
                        if (navegar) {
                            navController.navigate(
                                Destinations.Contratacion.route
                                        + "/${cuidador!!.idUsuario}/${cuidador!!.servicio!!.idOferta}" +
                                        "/${homeViewModel.idMascota.value}"
                            )
                        } else {
                            mostrarTexto = true
                        }

                    }
                }
            )
        }
    }
}


@Composable
fun RationaleAlert(onDismiss: () -> Unit, onConfirm: () -> Unit) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "We need location permissions to use this app",
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyBoxMascotaHome(mascota: Mascota, homeViewModel: HomeViewModel) {


    Image(
        ImageConverter.byteArrayToImageBitmap(mascota.imagen!!),
        contentDescription = "Imagen mascota",
        Modifier
            .fillMaxSize()
            .clip(CircleShape),
        contentScale = ContentScale.FillBounds,
    )

    Spacer(modifier = Modifier.width(15.dp))

}

fun valoracionMedia(valoraciones: List<Valoracion>?): Double {
    var resultado = 0.0
    if (valoraciones != null) {
        for (valoracion in valoraciones) {
            resultado += valoracion.puntuacion
        }
        resultado /= valoraciones.size
    }

    return resultado
}
