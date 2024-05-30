package com.example.pataventura.ui.screens.calendario.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun BodyCalendario(){
    Box(){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier
                .align(Alignment.TopStart)
                .fillMaxSize()
                .padding(top = 300.dp))

        CalendarScreen()
        //EventCard()
        /*Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)){

            }*/

            /*Spacer(modifier = Modifier.width(20.dp))
            LazyColumn(Modifier.padding(25.dp)){
                item {
                    MyCardServicio()
                    MyCardServicio()
                    MyCardServicio()
                    MyCardServicio()
                    MyCardServicio()
                    MyCardServicio()


                }
            }

        }*/
    }
}

@Composable
fun MyCardServicio() {
    var horaServicio= "16:00 - 17:00"
    var servcio = "Paseo"
    var nombreMascota = "Tyrion"
    var nombreCuidador = "Fabiola Ulin"
    var precio = "15€"
    val color = Color.Blue

    Card (modifier = Modifier
        .padding(bottom = 15.dp)
        .fillMaxWidth()
        .height(150.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(0.5f)),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Verde)
    ){
        Row(
            Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyColumnMascota(servcio, nombreMascota, color, horaServicio)
            Spacer(modifier = Modifier.size(10.dp))
            MyColumnCuidador(nombreCuidador, precio)
        }

    }

}

@Composable
fun MyColumnCuidador(nombreCuidador: String, precio: String) {
    Column (
        Modifier
            .padding(5.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (){
            Image(painter = painterResource(id = R.drawable.imagen_perfil),
                contentDescription = null, Modifier.size(50.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Column (){
                CustomText(text = nombreCuidador, color = Color.Black,
                    fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
                CustomText(text = precio, color = Color.Black,
                    fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
                RowValoracion()
            }

        }
        Spacer(modifier = Modifier.size(10.dp))

        Box(modifier = Modifier.padding(5.dp)){
            MyCustomButton(texto = "Cancelar", color = Color.Red){}
        }

    }
}

@Composable
fun RowValoracion() {
    Row (Modifier.padding()){
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(15.dp),tint = Verde
        )
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(15.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(15.dp),tint = Verde
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
fun MyColumnMascota(servicio: String, nombreMascota: String, color: Color, horaServicio: String) {
    Column (
        Modifier
            .padding(start = 10.dp)
            .width(120.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        RowCita(color, horaServicio)
        CustomText(text = "$servicio $nombreMascota", color = Color.Black, fontSize =16.sp ,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
        Image(painter = painterResource(id = R.drawable.imagen_boton_perro),
            contentDescription = null , Modifier.size(50.dp))
    }

}

@Composable
fun RowCita(color: Color, horaServicio: String) {
    Row (Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ){
        Column(
            Modifier
                .size(20.dp)
                .border(2.dp, color, RoundedCornerShape(100f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

            ){
            Box(modifier = Modifier
                .size(3.dp)
                .border(3.dp, color, RoundedCornerShape(100f)))
        }

        CustomText(text = horaServicio, color = Color.Black, fontSize = 14.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
    }
}



@Composable
fun CalendarScreen() {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val currentDate = LocalDate.now()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8BC34A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado del calendario
        CalendarHeader(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
        )

        // Días del calendario
        CalendarDays(
            yearMonth = currentMonth,
            currentDate = currentDate
        )

        // Evento del día

    }
}

@Composable
fun CalendarHeader(currentMonth: YearMonth, onPreviousMonth: () -> Unit, onNextMonth: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Month", tint = Color.White)
        }

        Text(
            text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        IconButton(onClick = onNextMonth) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Next Month", tint = Color.White)
        }
    }
}

@Composable
fun CalendarDays(yearMonth: YearMonth, currentDate: LocalDate) {
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfMonth = yearMonth.atDay(1).dayOfWeek.value % 7
    val weeksInMonth = (daysInMonth + firstDayOfMonth + 6) / 7

    Column {
        // Días de la semana
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("D", "L", "M", "X", "J", "V", "S").forEach { day ->
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
            Row(modifier = Modifier.fillMaxWidth()) {
                for (day in 0..6) {
                    val dayOfMonth = week * 7 + day - firstDayOfMonth + 1
                    if (dayOfMonth in 1..daysInMonth) {
                        DayCell(dayOfMonth, isCurrentDay = dayOfMonth == currentDate.dayOfMonth)
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun DayCell(day: Int, isCurrentDay: Boolean) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .aspectRatio(1f)
            .padding(4.dp)
            .background(if (isCurrentDay) Color.Yellow else Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$day")
    }
}

@Composable
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
    )  {
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
                // Aquí puedes agregar una imagen de perro usando Coil o Glide para Compose
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
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalendarApp()
}*/