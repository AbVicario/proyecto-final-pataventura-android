package com.example.pataventura.ui.screens.contratacion.composables

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.ui.composables.CustomOutlinedTextField
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.DatePickerWithDialog
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.composables.MyValoracionStars
import com.example.pataventura.ui.screens.contratacion.ContratacionViewModel
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BodyContratacion(
    contratacionViewmodel: ContratacionViewModel,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val valoraciones: List<Valoracion> by contratacionViewmodel.valoraciones.observeAsState(
        emptyList()
    )
    val cuidador: Cuidador by contratacionViewmodel.cuidador.observeAsState(Cuidador())
    val servicio: String by contratacionViewmodel.servicio.observeAsState("")
    val precioTotal: String by contratacionViewmodel.precioTotal.observeAsState("")

    LaunchedEffect(key1 = Any()) {
        contratacionViewmodel.setCuidador(homeViewModel.listaCuidadores.value!!)
    }
    Box(modifier = Modifier.padding(horizontal = 15.dp)) {
        Image(
            painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Foondo",
            Modifier.fillMaxSize()
        )
        LazyColumn(
            Modifier
                .fillMaxWidth()
        ) {
            item {
                MyCardPerfil(cuidador, valoraciones)
                Spacer(modifier = Modifier.height(15.dp))
                Column(Modifier.fillMaxWidth().padding(horizontal = 15.dp)) {
                    CustomText(
                        text = if (servicio.lowercase() == "paseo") "Paseo"
                        else "Guardería" + ":", color = Verde, fontSize = 22.sp,
                        fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomText(
                        text = cuidador.servicio!!.descripcion, color = Color.Black, fontSize = 22.sp,
                        fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        val text = if (servicio.lowercase() == "paseo") "por paseo" else "día"
                        CustomText(
                            text = cuidador.servicio!!.precio.toString() + "€/" + text,
                            color = Verde, fontSize = 22.sp,
                            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                MyRowFechas(servicio, cuidador.servicio!!.precio, contratacionViewmodel)
                Spacer(modifier = Modifier.height(15.dp))
                CustomOutlinedTextField(
                    onValueChange = { contratacionViewmodel.onNotasChange(it) },
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .height(120.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Notas",
                    trailingIcon = {},
                    supportingText = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false
                )
                Spacer(modifier = Modifier.height(15.dp))
                MyRowPrecio(cuidador.servicio!!.precio, servicio, precioTotal)
                Spacer(modifier = Modifier.height(15.dp))
                MyRowButtons(navController)
            }
        }
    }
}

@Composable
fun MyRowButtons(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MyCustomButton(texto = "Contartar", color = Verde) {

        }
        Spacer(modifier = Modifier.width(40.dp))
        MyCustomButton(texto = "Cancelar", color = Color.Red) {
            navController.navigate("home")
        }
    }
}


@Composable
fun MyRowPrecio(precio: Float, servicio: String, precioTotal: String) {
    Row(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        CustomText(
            text = "Precio total:", color = Color.Black, fontSize = 22.sp,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
        )
        CustomText(
            text = if(servicio.lowercase() == "paseo") precio.toString() else precioTotal,
            color = Color.Black, fontSize = 22.sp,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
        )
    }
}


@Composable
fun MyRowFechas( servicio: String, precio: Float, contratacionViewmodel: ContratacionViewModel) {
    var dateTimeInicio by remember { mutableStateOf<LocalDateTime?>(LocalDateTime.now().plusDays(1)) }
    var dateTimeFin by remember { mutableStateOf<LocalDateTime?>(if(servicio.lowercase() == "paseo") dateTimeInicio!!.plusHours(1)
    else dateTimeInicio!!.plusDays(1))}
    var isSelected by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth()
        .padding(horizontal = 12.dp)) {
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom) {
            CustomText(
                text = "Inicio:", color = Verde, fontSize = 22.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
            DatePickerWithDialog(
                true,
                servicio = servicio,
                value = dateTimeInicio,
                dateFormatter = ::formatDate,
                enabled = true,
                placeholder = {},
                dateValidator = { true },
                minDateTime = LocalDateTime.now().plusDays(1),
                onChange = { newDateTime ->
                    dateTimeInicio = newDateTime
                }
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom) {
            CustomText(
                text = "Fin:", color = Verde, fontSize = 22.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
            Log.d("TAG", "MyRowFechas: $dateTimeInicio")
            DatePickerWithDialog(
                false,
                servicio = servicio,
                value = if(isSelected)dateTimeFin else dateTimeInicio,
                dateFormatter = ::formatDate,
                enabled = servicio.lowercase() != "paseo",
                placeholder = {},
                dateValidator = { true },
                minDateTime = dateTimeInicio!!,
                onChange = { newDateTime ->
                    dateTimeFin = newDateTime
                    isSelected = true
                    val numDias = if (servicio.lowercase() == "paseo") 1
                    else ChronoUnit.DAYS.between(dateTimeInicio, dateTimeFin).toInt()
                    contratacionViewmodel.calcularPrecio(precio,numDias)
                }

            )
        }
    }
}
fun formatDate(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    return date.format(formatter)
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

@Composable
fun MyCardPerfil(
    cuidador: Cuidador,
    valoraciones: List<Valoracion>
){
    val media = valoracionMedia(valoraciones)

    Card(
        modifier = Modifier.padding(10.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Verde)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
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
                        bitmap = ImageConverter.byteArrayToImageBitmap(cuidador.imagen),
                        contentDescription = "Imagen cuidador",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = cuidador.alias, color = Color.Black, fontSize = 30.sp,
                    fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
                )
                Spacer(modifier = Modifier.size(5.dp))
                Row(Modifier.padding(start = 10.dp)) {
                    MyValoracionStars(valoracion = media, sizeStars = 40)
                }
            }
        }

    }

}

fun dateValidator(dateMillis: Long): Boolean {
    val selectedDate = Instant.ofEpochMilli(dateMillis).atZone(ZoneId.systemDefault()).toLocalDate()
    val currentDate = LocalDate.now()
    return !selectedDate.isBefore(currentDate)
}
