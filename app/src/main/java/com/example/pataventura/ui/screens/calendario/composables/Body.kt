package com.example.pataventura.ui.screens.calendario.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.DemandaAceptada
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.composables.ObtenerColor
import com.example.pataventura.ui.screens.calendario.CalendarioViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Marron
import com.example.pataventura.ui.theme.Tierra
import com.example.pataventura.ui.theme.Verde
import com.example.pataventura.ui.theme.VerdeOliva
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun BodyCalendario(calendarioViewModel: CalendarioViewModel) {
    val demandas: List<DemandaAceptada> by calendarioViewModel.demandas.observeAsState(emptyList())
    val demandasDay: List<DemandaAceptada> by calendarioViewModel.demandasDay.observeAsState(emptyList())
    val rol = RoleHolder.rol.value.toString().lowercase()
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
        ) {
            CalendarScreen(demandas, calendarioViewModel)
            //EventCard()

            Spacer(modifier = Modifier.width(20.dp))
            LazyColumn(Modifier.padding(25.dp)) {
                items(demandasDay.size) { index ->
                    demandasDay.getOrNull(index).let { demanda ->
                        if (demanda != null) {
                            MyCardServicio(rol, demanda)
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }

        }
    }

}


@Composable
fun MyCardServicio(
    rol: String,
    demanda: DemandaAceptada
) {
    Card(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(0.5f)
        ),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Verde)
    ) {
        Row(
            Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyColumnMascota(demanda)
            Spacer(modifier = Modifier.size(10.dp))
            MyColumnCuidador(
                demanda, rol
            )
        }

    }

}

@Composable
fun MyColumnCuidador(demanda: DemandaAceptada, rol: String) {
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Row() {
            Image(
                ImageConverter.byteArrayToImageBitmap(demanda.cuidador.imagen),
                contentDescription = null,
                Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.size(5.dp))
            CustomText(
                text = demanda.cuidador.nombre, color = Color.Black,
                fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
        }

        Spacer(modifier = Modifier.size(5.dp))
        Column(Modifier.fillMaxHeight()) {
            CustomText(
                text = demanda.oferta.descripcion, color = Color.Black,
                fontSize = 16.sp, fontWeight = FontWeight.Normal,
                fontFamily = CustomFontFamily
            )
            Spacer(modifier = Modifier.size(5.dp))
            CustomText(
                text = "Total: ${demanda.precio} €", color = Color.Black,
                fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            MyCustomButton(texto = "Cancelar", color = Color.Red) {}
        }

    }
}

@Composable
fun RowValoracion() {
    Row(Modifier.padding()) {
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(15.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(15.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(15.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarHalf, contentDescription = null,
            Modifier.size(15.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarOutline, contentDescription = null,
            Modifier.size(15.dp), tint = Verde
        )
    }
}

@Composable
fun MyColumnMascota(demanda: DemandaAceptada) {
    var fecha = demanda.fechaInicio.slice(0..9)
    var hora = demanda.fechaInicio.slice(11..15)

    Column(
        Modifier
            .padding(start = 10.dp)
            .width(150.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        RowCita(demanda)
        Spacer(modifier = Modifier.size(5.dp))

        CustomText(
            text = demanda.oferta.tipo, color = Color.Black, fontSize = 16.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
        Spacer(modifier = Modifier.size(5.dp))
        CustomText(
            text = demanda.descripcion, color = Color.Black, fontSize = 16.sp,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
        )
        Spacer(modifier = Modifier.size(5.dp))
        Column {
            CustomText(
                text = "Fecha: ", color = Color.Black, fontSize = 16.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
            Spacer(modifier = Modifier.size(5.dp))
            CustomText(
                text = fecha, color = Color.Black, fontSize = 16.sp,
                fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
            )
        }

        Spacer(modifier = Modifier.size(5.dp))
        Row {
            CustomText(
                text = "Hora: ", color = Color.Black, fontSize = 16.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
            CustomText(
                text = hora, color = Color.Black, fontSize = 16.sp,
                fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily
            )
        }
    }
}

@Composable
fun RowCita(demanda: DemandaAceptada) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier
                .size(53.dp)
                .clip(CircleShape)
                .background(ObtenerColor(demanda.mascota.color)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                ImageConverter.byteArrayToImageBitmap(demanda.mascota.imagen!!),
                contentDescription = null,
                Modifier
                    .size(47.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        CustomText(
            text = demanda.mascota.nombre, color = Color.Black, fontSize = 16.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
    }
}


@Composable
fun CalendarScreen(
    demandas: List<DemandaAceptada>,
    calendarioViewModel: CalendarioViewModel
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val currentDate = LocalDate.now()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.525f)
            .background(Verde, RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CalendarHeader(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
        )
        Spacer(modifier = Modifier.height(10.dp))

        CalendarDays(
            demandas,
            calendarioViewModel,
            yearMonth = currentMonth,
            currentDate = currentDate,
            month = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )

        // Evento del día

    }
}

@Composable
fun CalendarHeader(currentMonth: YearMonth, onPreviousMonth: () -> Unit, onNextMonth: () -> Unit) {
    val year = currentMonth.year
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        CustomText(
            text = year.toString(),
            color = Color.White,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily,
            modifier = Modifier.padding(horizontal = 130.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousMonth) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Previous Month",
                    tint = Color.White
                )
            }
            Text(
                text = currentMonth.month.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                ),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            IconButton(onClick = onNextMonth) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Next Month",
                    tint = Color.White
                )
            }
        }
    }

}

@Composable
fun CalendarDays(
    demandasAceptadas: List<DemandaAceptada>,
    calendarioViewModel: CalendarioViewModel,
    yearMonth: YearMonth,
    currentDate: LocalDate,
    month: String
) {
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfMonth = yearMonth.atDay(1).dayOfWeek.value % 7
    val weeksInMonth = (daysInMonth + firstDayOfMonth + 6) / 7
    val rol = RoleHolder.rol.value.toString().lowercase()

    Column {
        // Días de la semana
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            listOf("L", "M", "X", "J", "V", "S", "D").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Días del mes
        for (week in 0 until weeksInMonth) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                for (day in 0..6) {
                    val dayOfMonth = week * 7 + day - firstDayOfMonth + 2
                    if (dayOfMonth in 1..daysInMonth) {
                        var isCurrentDay = false
                        if (dayOfMonth == currentDate.dayOfMonth && month == LocalDate.now().month.getDisplayName(
                                TextStyle.FULL,
                                Locale.getDefault()
                            )
                        ) {
                            isCurrentDay = true
                        }
                        DayCell(
                            demandasAceptadas,
                            calendarioViewModel,
                            dayOfMonth,
                            isCurrentDay = isCurrentDay,
                            listOf(Color.White, Tierra),
                            rol,
                            isDemanda = true,
                            onDayClick = { clickedDay ->
                                calendarioViewModel.selectedDate.value = LocalDate.of(yearMonth.year, yearMonth.month, clickedDay)
                            },
                            yearMonth
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(0.8f))
                    }
                }
            }
        }
    }
}

@Composable
fun DayCell(
    demandasAceptadas: List<DemandaAceptada>,
    calendarioViewModel: CalendarioViewModel,
    day: Int,
    isCurrentDay: Boolean,
    colores: List<Color>,
    rol: String,
    isDemanda: Boolean,
    onDayClick: (Int) -> Unit,
    yearMonth: YearMonth
) {

    var index = 0
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(54.dp)
            .aspectRatio(1f)
            .padding(8.dp)
            .background(if (isCurrentDay) VerdeOliva else Verde, CircleShape)
            .clickable {
                calendarioViewModel.demandasDay.value = emptyList()
                onDayClick(day)
                calendarioViewModel.demandasDay.value = getDemandasDay(demandasAceptadas, calendarioViewModel)
            },
        contentAlignment = Alignment.Center
    ) {
        CustomText(
            text = "$day",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily
        )
            if (rol == "tutor") {
                while (colores.size - 1 >= index) {
                    MyBoxColor(colores[index], index, colores.size)
                    index++
                }
            } else {
                val date = LocalDate.of(yearMonth.year, yearMonth.month, day)
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val formattedDate = date!!.format(formatter)
                for (demanda in demandasAceptadas) {
                    if (demanda.fechaInicio.slice(0..9) == formattedDate) {
                        MyBoxColor(Marron, 1, 1)
                    }
                }
            }
    }
}

@Composable
fun MyBoxColor(color: Color, index: Int, totalColors: Int) {
    val offset = when (index) {
        1 -> Modifier.offset(x = 4.dp, y = 4.dp)
        2 -> Modifier.offset(x = 20.dp, y = 4.dp)
        3 -> Modifier.offset(x = 36.dp, y = 4.dp)
        4 -> Modifier.offset(x = 4.dp, y = 36.dp)
        5 -> Modifier.offset(x = 20.dp, y = 36.dp)
        6 -> Modifier.offset(x = 36.dp, y = 36.dp)
        else -> Modifier.offset(x = 20.dp, y = 20.dp)
    }
    Box(
        modifier = Modifier
            .size(4.dp)
            .then(offset)
            .background(color, CircleShape)
    )
}

fun getDemandasDay(demandas: List<DemandaAceptada>, calendarioViewModel: CalendarioViewModel): List<DemandaAceptada> {
    val date = calendarioViewModel.selectedDate.value
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = date!!.format(formatter)
    val demandasDay = mutableListOf<DemandaAceptada>()
    for (demanda in demandas) {
        if (demanda.fechaInicio.slice(0..9) == formattedDate) {
            demandasDay.add(demanda)
        }
    }
    return demandasDay
}

/*@Composable
fun EventCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "16:00 - 17:00",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Paseo Lola",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "15€",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Green
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Acción de cancelar */ },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "Cancelar", color = Color.White)
            }
        }
    }
}*/
