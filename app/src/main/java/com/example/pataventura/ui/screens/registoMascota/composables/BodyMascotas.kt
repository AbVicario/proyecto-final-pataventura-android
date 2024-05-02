package com.example.pataventura.ui.screens.registoMascota.composables

import android.media.CamcorderProfile
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CampoObligatorioText
import com.example.pataventura.ui.composables.CustomOutlinedTextField
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldDes
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldDesColor
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.IconButtonImageMascota
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyAlertDialog
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyRegistroMascota(registroMascotaViewModel: RegistroMascotaViewModel,
                        navController:NavController) {
    val nombreEmpty: Boolean by registroMascotaViewModel.nombreEmpty.observeAsState(false)
    val numChipEmpty: Boolean by registroMascotaViewModel.numChipEmpty.observeAsState(false)
    val tipoEmpty: Boolean by registroMascotaViewModel.tipoEmpty.observeAsState(false)
    val colorEmpty: Boolean by registroMascotaViewModel.colorEmpty.observeAsState(false)
    val raza : String by registroMascotaViewModel.raza.observeAsState("")
    val tipo : String by registroMascotaViewModel.tipo.observeAsState("")
    val edad : String by registroMascotaViewModel.edad.observeAsState("")
    var itemsTipo = listOf<String>(
        "Perro",
        "Gato"
    )
    var itemsColores = listOf<String>(
        "Rojo",
        "Azul",
        "Verde",
        "Negro"
    )
    var itemsEdad = listOf<String>(
        "Cachorro",
        "Adulto",
        "Senior"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_registro_mascotas),
            contentDescription = "Silueta gato", Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 15.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            CustomText(
                text = "Introduce los datos de tu mascota",
                color = Verde, fontSize = 20.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )

            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {

                IconButtonImageMascota()
                Spacer(modifier = Modifier.width(10.dp))
                CustomOutlinedTextField(
                    onValueChange = { registroMascotaViewModel.onNombreChange(it) },
                    Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Nombre",
                    leadingIcon = { Icon(Icons.Default.Pets, null) },
                    supportingText = {if(nombreEmpty) CampoObligatorioText() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )
            }
            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Box(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 5.dp)){
                    CustomOutlinedTextFieldDes(
                        text = tipo,
                        items = itemsTipo,
                        onValueChange = {registroMascotaViewModel.onTipoChange(it)
                            registroMascotaViewModel.onRazaChange(" ")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        enabled = true,
                        readOnly = true,
                        placeholder = "Tipo",
                        supportingText = {if(tipoEmpty) CampoObligatorioText()},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextFieldDes(
                        text = raza,
                        items = registroMascotaViewModel.listaRaza,
                        onValueChange = {registroMascotaViewModel.onRazaChange(it)},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        enabled = true,
                        readOnly = true,
                        placeholder = "Raza",
                        supportingText = {},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )

                }
            }

            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Box(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextFieldDes(
                        text = edad,
                        items = itemsEdad,
                        onValueChange = {registroMascotaViewModel.onEdadChange(it)},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        enabled = true,
                        readOnly = true,
                        placeholder = "Edad",
                        supportingText = {},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextField(
                        onValueChange = { registroMascotaViewModel.onPesoChange(it) },
                        Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        enabled = true,
                        readOnly = false,
                        placeholder = "Peso",
                        supportingText = { },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }

            CustomOutlinedTextField(
                onValueChange = { registroMascotaViewModel.onDescripcionChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Descripción",
                supportingText = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = false
            )

            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Box(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextField(
                        onValueChange = { registroMascotaViewModel.onNumChipChange(it) },
                        Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        enabled = true,
                        readOnly = false,
                        placeholder = "Nº Chip",
                        supportingText = {if(numChipEmpty) CampoObligatorioText() },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = false
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextFieldDesColor(
                        items = itemsColores,
                        onItemSelected = {registroMascotaViewModel.oncolorAsigChange(it)},
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        enabled = true,
                        readOnly = true,
                        placeholder = "Color asignado",
                        supportingText = {if(colorEmpty) CampoObligatorioText()},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                }
            }

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(40.dp)
                )
            {
                MyAlertDialog(
                    show = registroMascotaViewModel.showDialog,
                    icon = Icons.Default.Error,
                    onConfirm = { registroMascotaViewModel.onDialogConfirm(navController) },
                    dialogTitle = "Error",
                    dialogText ="Ha habido un error al completar el registro. Recuerde registrar una mascota" +
                            "para poder solicitar demandar servicios." +
                            " Intentelo mas tarde"
                )
                LoginButton(text = "Finalizar",
                    onClick = {registroMascotaViewModel.onFinalizarPressLaunch(navController)} )
            }
        }
    }
}


