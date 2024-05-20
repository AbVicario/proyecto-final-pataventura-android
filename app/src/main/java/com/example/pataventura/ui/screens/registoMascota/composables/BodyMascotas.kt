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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
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
fun BodyRegistroMascota(
    registroMascotaViewModel: RegistroMascotaViewModel,
    navController: NavController
) {
    val nombreEmpty: Boolean by registroMascotaViewModel.nombreEmpty.observeAsState(false)
    val numChipEmpty: Boolean by registroMascotaViewModel.numChipEmpty.observeAsState(false)
    val tipoEmpty: Boolean by registroMascotaViewModel.tipoEmpty.observeAsState(false)
    val colorEmpty: Boolean by registroMascotaViewModel.colorEmpty.observeAsState(false)

    val raza: String by registroMascotaViewModel.raza.observeAsState("")
    val tipo: String by registroMascotaViewModel.tipo.observeAsState("")
    val edad: String by registroMascotaViewModel.edad.observeAsState("")
    val tamanyo: String by registroMascotaViewModel.tamanyo.observeAsState("")
    val sexo: String by registroMascotaViewModel.sexo.observeAsState("")


    val context = LocalContext.current
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

    var itemsTamanyo = listOf<String>(
        "Pequeño",
        "Mediano",
        "Grande"
    )

    var itemsSexo = listOf<String>(
        "Macho",
        "Hembra"
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
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 15.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            item {
                CustomText(
                    text = "Introduce los datos de tu mascota",
                    color = Verde, fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
                )
                MyRowNombre(nombreEmpty, registroMascotaViewModel)
                Spacer(modifier = Modifier.height(4.dp))
                MyRowRaza(tipo, raza, tipoEmpty, itemsTipo, registroMascotaViewModel)
                MyRowEdad(edad, sexo, itemsEdad, itemsSexo, registroMascotaViewModel)
                MyRowPeso(tamanyo, itemsTamanyo, registroMascotaViewModel)

                CustomOutlinedTextField(
                    onValueChange = { registroMascotaViewModel.onObservacionChange(it) },
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Observaciones",
                    supportingText = { },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = false
                )
                MyRowNumChip(
                    numChipEmpty,
                    itemsColores,
                    registroMascotaViewModel,
                    colorEmpty
                )


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
                        dialogText = "Ha habido un error al completar el registro. Recuerde registrar una mascota" +
                                "para poder solicitar demandar servicios." +
                                " Intentelo mas tarde"
                    )
                    LoginButton(text = "Finalizar",
                        onClick = {
                            registroMascotaViewModel.onFinalizarPress(
                                navController,
                                context
                            )
                        })
                }
            }


        }
    }
}

@Composable
fun MyRowNumChip(
    numChipEmpty: Boolean,
    itemsColores: List<String>,
    registroMascotaViewModel: RegistroMascotaViewModel,
    colorEmpty: Boolean
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
            Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 5.dp)
        )
        {
            CustomOutlinedTextField(
                onValueChange = { registroMascotaViewModel.onNumChipChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Nº Chip",
                supportingText = { if (numChipEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = false
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        )
        {
            CustomOutlinedTextFieldDesColor(
                items = itemsColores,
                onItemSelected = { registroMascotaViewModel.onColorAsigChange(it) },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = true,
                readOnly = true,
                placeholder = "Color asignado",
                supportingText = { if (colorEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }
    }
}

@Composable
fun MyRowPeso(
    tamanyo: String,
    itemsTamanyo: List<String>,
    registroMascotaViewModel: RegistroMascotaViewModel,
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .fillMaxWidth(0.495f)
                .padding(horizontal = 5.dp)
        )
        {
            CustomOutlinedTextField(
                onValueChange = { registroMascotaViewModel.onPesoChange(it.toDouble()) },
                modifier = Modifier
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
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        )
        {
            CustomOutlinedTextFieldDes(
                items = itemsTamanyo,
                text = tamanyo,
                onValueChange = { registroMascotaViewModel.onTamanyoChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = true,
                readOnly = true,
                placeholder = "Tamaño",
                supportingText = {},
                singleLine = true
            )
        }

    }
}

@Composable
fun MyRowEdad(
    edad: String,
    sexo: String,
    itemsEdad: List<String>,
    itemsSexo: List<String>,
    registroMascotaViewModel: RegistroMascotaViewModel,
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
            Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldDes(
                text = edad,
                items = itemsEdad,
                onValueChange = {
                    registroMascotaViewModel.onEdadChange(it)
                },
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
                .padding(horizontal = 5.dp)
        )
        {
            CustomOutlinedTextFieldDes(
                text = sexo,
                items = itemsSexo,
                onValueChange = { registroMascotaViewModel.onSexoChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = true,
                readOnly = true,
                placeholder = "Sexo",
                supportingText = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

        }
    }
}

@Composable
fun MyRowRaza(
    tipo: String,
    raza: String,
    tipoEmpty: Boolean,
    itemsTipo: List<String>,
    registroMascotaViewModel: RegistroMascotaViewModel,
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
            Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldDes(
                text = tipo,
                items = itemsTipo,
                onValueChange = {
                    registroMascotaViewModel.onTipoChange(it)
                    registroMascotaViewModel.onRazaChange(" ")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = true,
                readOnly = true,
                placeholder = "Tipo",
                supportingText = { if (tipoEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        )
        {
            CustomOutlinedTextFieldDes(
                text = raza,
                items = registroMascotaViewModel.listaRaza,
                onValueChange = { registroMascotaViewModel.onRazaChange(it) },
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
}

@Composable
fun MyRowNombre(
    nombreEmpty: Boolean,
    registroMascotaViewModel: RegistroMascotaViewModel,
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        IconButtonImageMascota(registroMascotaViewModel)
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
            supportingText = { if (nombreEmpty) CampoObligatorioText() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
    }
}


