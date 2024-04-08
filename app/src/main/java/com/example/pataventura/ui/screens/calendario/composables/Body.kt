package com.example.pataventura.ui.screens.calendario.composables

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.DatePickerWithDialog
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import java.time.LocalDate


@Composable
fun BodyCalendario(){
    Box(){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier
                .align(Alignment.TopStart)
                .fillMaxSize()
                .padding(top = 300.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)){

            }
            //MyCalendar()
            Spacer(modifier = Modifier.width(20.dp))
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

        }
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
            MyCustomButton(texto = "Cancelar", color = Color.Red)
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
