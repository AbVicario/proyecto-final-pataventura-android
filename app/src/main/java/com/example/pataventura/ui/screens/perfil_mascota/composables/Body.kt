package com.example.pataventura.ui.screens.perfil_mascota.composables

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextContrato
import com.example.pataventura.ui.composables.CustomOutlinedTextField
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldDes
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascota
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascotaDesplegable
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyPerfilMascotas(perfilMascotaViewModel: PerfilMascotaViewModel,
                       navController: NavController){
    var colorMascota = Color.Blue
    var nombreMascota = "Nombre"
    Box() {
        Image(
            painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowNombre(colorMascota, nombreMascota)
            Spacer(modifier = Modifier.size(10.dp))
            ColumnCaracteristicas()
        }
    }
}

@Composable
fun ColumnCaracteristicas() {
    var razas = listOf("pekines", "bichon")
    var sexo = listOf("macho", "hembra")
    Column (verticalArrangement = Arrangement.SpaceBetween){
        CustomOutlinedTextFieldDes(
            items = razas,
            onItemSelected ={} ,
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Raza",
            supportingText = {},
            singleLine = true
        )
        Spacer(modifier = Modifier.size(10.dp))
        Row (){
            Box(modifier = Modifier.fillMaxWidth(0.5f)){
                CustomOutlinedTextFieldDes(
                    items = sexo,
                    onItemSelected ={} ,
                    onValueChange = {},
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Sexo",
                    supportingText = {},
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            CustomOutlinedTextField(
                onValueChange = {},
                Modifier.fillMaxWidth().height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Peso",
                supportingText = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Box(Modifier.fillMaxWidth(0.3f)){
                CustomOutlinedTextField(
                    onValueChange = {},
                    Modifier.fillMaxWidth().height(100.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Edad",
                    supportingText = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            CustomOutlinedTextField(
                onValueChange = {},
                Modifier.fillMaxWidth().height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Nº Chip",
                supportingText = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        CustomOutlinedTextField(
            onValueChange = {},
            Modifier.fillMaxWidth().height(220.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Descrpción",
            supportingText = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = false
        )
    }
}

@Composable
fun RowNombre(colorMascota: Color, nonbreMascota:String) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Box(
            Modifier
                .size(40.dp)
                .background(colorMascota, RoundedCornerShape(100f))){
        }
        Spacer(modifier = Modifier.size(15.dp))

        CustomText(text = nonbreMascota, color = Verde, fontSize = 30.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
    }
}
