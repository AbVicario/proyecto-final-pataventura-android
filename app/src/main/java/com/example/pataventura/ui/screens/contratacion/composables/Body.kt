package com.example.pataventura.ui.screens.contratacion.composables
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextContrato
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascota
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.DatePickerWithDialog
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import java.time.LocalDate
@Composable
fun BodyContratacion(){
    Box(){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Foondo",
            Modifier.fillMaxSize())

        Column(
            Modifier
                .fillMaxWidth()
                .height(700.dp)
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            MyCardPerfil()

            MyRowFechas("Fecha inicio:", "Fecha fin:")

            CustomOutlinedTextContrato(
                singleLine = false,
                placeholder = "Notas:",
                keyboardType = KeyboardType.Text,
                opcional = true,
                altura = 260,
                onTextFieldChange = {}
            )

            MyRowPrecio()

            MyRowButtons()
        }
    }



}

@Composable
fun MyRowButtons() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        MyCustomButton(texto = "Contartar", color = Verde)
        Spacer(modifier = Modifier.width(40.dp))
        MyCustomButton(texto = "Cancelar", color = Color.Red)
    }
}




@Composable
fun MyRowPrecio() {
    Row(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom){
        CustomText(text = "Precio total:", color = Color.Black, fontSize = 16.sp ,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily)
        CustomText(text = "15€" , color = Color.Black , fontSize =16.sp ,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily)
    }
}

@Composable
fun MyRowFechas(textoInicio: String, textoFin: String) {
    Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Box(modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 8.dp)){
            DatePickerWithDialog(
                enabled = true,
                dateFormatter = { it.toString() },
                value = LocalDate.of(2022, 10, 20),
                placeholder = {
                    Text(textoInicio)
                }) {
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            DatePickerWithDialog(
                enabled = true,
                dateFormatter = { it.toString() },
                value = LocalDate.of(2022, 10, 20),
                placeholder = {
                    Text(textoFin)
                }) {
            }
        }


    }

}



@Composable
fun MyCardPerfil() {
    Card (modifier = Modifier.padding(10.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(),
        border = CardDefaults.outlinedCardBorder()
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier
                        .size(130.dp)
                        .padding(15.dp)){
                    Image(painter = painterResource(id = R.drawable.imagen_perfil),
                        contentDescription = "Imagen de perfil", Modifier.fillMaxSize() )
                }
                Row (Modifier.padding(start=10.dp)){
                    Icon(Icons.Default.StarRate, contentDescription = null,
                        Modifier.size(30.dp),tint = Verde)
                    Icon(Icons.Default.StarRate, contentDescription = null,
                        Modifier.size(30.dp), tint = Verde)
                    Icon(Icons.Default.StarRate, contentDescription = null,
                        Modifier.size(30.dp),tint = Verde)
                    Icon(Icons.Default.StarHalf, contentDescription = null,
                        Modifier.size(30.dp), tint = Verde)
                    Icon(Icons.Default.StarOutline, contentDescription = null,
                        Modifier.size(30.dp), tint = Verde)
                }


            }
            Spacer(modifier = Modifier.size(10.dp))
            Column() {
                CustomText(text = "Nombre Cuidador", color = Color.Black, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold , fontFamily = CustomFontFamily)
                Spacer(modifier = Modifier.size(5.dp))
                CustomText(text = "Rol cuidador", color = Color.Black, fontSize = 20.sp ,
                    fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily)
                Spacer(modifier = Modifier.size(5.dp))
                CustomText(text = "Descripción del cuidador", color = Color.Black, fontSize = 16.sp,
                    fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily)
            }
        }

    }

}
