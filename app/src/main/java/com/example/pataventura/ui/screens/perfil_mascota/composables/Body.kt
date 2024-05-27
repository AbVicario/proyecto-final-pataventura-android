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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CampoObligatorioText
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldUpdate
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldUpdateDes
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyAlertDialog
import com.example.pataventura.ui.composables.MyAlertDialogDimiss
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyPerfilMascotas(
    perfilMascotaViewModel: PerfilMascotaViewModel,
    navController: NavController,
    editMode: Boolean
) {
    val nombre by perfilMascotaViewModel.nombre.observeAsState("")
    val edad by perfilMascotaViewModel.edad.observeAsState("")
    val raza by perfilMascotaViewModel.raza.observeAsState("")
    val sexo by perfilMascotaViewModel.sexo.observeAsState("")
    val color by perfilMascotaViewModel.color.observeAsState("")
    val observacion by perfilMascotaViewModel.observacion.observeAsState("")
    val tamanyo by perfilMascotaViewModel.tamanyo.observeAsState("")
    val tipo by perfilMascotaViewModel.tipo.observeAsState("")
    val peso by perfilMascotaViewModel.peso.observeAsState("")
    val numChip by perfilMascotaViewModel.numChip.observeAsState("")
    val tipoEmpty by perfilMascotaViewModel.tipoEmpty.observeAsState(false)
    val numChipEmpty by perfilMascotaViewModel.numChipEmpty.observeAsState(false)

    val itemsTipo = listOf<String>(
        "Perro",
        "Gato"
    )
    val itemsEdad = listOf<String>(
        "Cachorro",
        "Adulto",
        "Senior"
    )

    val itemsTamanyo = listOf<String>(
        "Pequeño",
        "Mediano",
        "Grande"
    )

    val itemsSexo = listOf<String>(
        "Macho",
        "Hembra"
    )



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
            RowNombre(
                perfilMascotaViewModel.obtenerColor(color),
                nombre,
                editMode,
                perfilMascotaViewModel,
                navController
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyColumnCaracteristicas(
                perfilMascotaViewModel, editMode, numChip, numChipEmpty, raza,
                sexo, observacion, tipo, tipoEmpty, itemsTipo, itemsEdad, itemsTamanyo,
                itemsSexo, edad, tamanyo, peso, navController
            )
        }
    }
}

@Composable
fun LazyColumnCaracteristicas(
    perfilMascotaViewModel: PerfilMascotaViewModel,
    editMode: Boolean,
    numChip: String,
    numChipEmpty: Boolean,
    raza: String,
    sexo: String,
    observacion: String,
    tipo: String,
    tipoEmpty: Boolean,
    itemsTipo: List<String>,
    itemsEdad: List<String>,
    itemsTamanyo: List<String>,
    itemsSexo: List<String>,
    edad: String,
    tamanyo: String,
    peso: String,
    navController: NavController
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            CustomOutlinedTextFieldUpdate(
                valueAux = numChip,
                onValueChange = { perfilMascotaViewModel.onNumChipChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "NºChip",
                supportingText = { if (numChipEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(modifier = Modifier.size(10.dp))
            RowTipoRaza(
                perfilMascotaViewModel,
                itemsTipo,
                tipo,
                raza,
                tipoEmpty,
                editMode
            )
            Spacer(modifier = Modifier.size(10.dp))
            RowEdadSexo(perfilMascotaViewModel, editMode, itemsEdad, itemsSexo, edad, sexo)
            Spacer(modifier = Modifier.size(10.dp))
            CustomOutlinedTextFieldUpdate(
                valueAux = observacion,
                onValueChange = { perfilMascotaViewModel.onObservacionChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Observaciones",
                supportingText = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = false
            )
            Spacer(modifier = Modifier.size(10.dp))
            RowTamanyoPeso(perfilMascotaViewModel, editMode, tamanyo, itemsTamanyo, peso)
        }
    }
}

@Composable
fun RowTipoRaza(
    perfilMascotaViewModel: PerfilMascotaViewModel,
    itemsTipo: List<String>,
    tipo: String,
    raza: String,
    tipoEmpty: Boolean,
    editMode: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(90.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldUpdateDes(
                valueAux = tipo,
                items = itemsTipo,
                onValueChange = {
                    perfilMascotaViewModel.onTipoChange(it)
                    perfilMascotaViewModel.onRazaChange(" ")
                },
                Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Tipo",
                supportingText = { if (tipoEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldUpdateDes(
                valueAux = raza,
                items = perfilMascotaViewModel.listaRaza,
                onValueChange = { perfilMascotaViewModel.onRazaChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Raza",
                supportingText = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }
    }

}

@Composable
fun RowEdadSexo(
    perfilMascotaViewModel: PerfilMascotaViewModel,
    editMode: Boolean,
    itemsEdad: List<String>,
    itemsSexo: List<String>,
    edad: String,
    sexo: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(90.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldUpdateDes(
                valueAux = edad,
                items = itemsEdad,
                onValueChange = {
                    perfilMascotaViewModel.onEdadChange(it)
                },
                Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Edad",
                supportingText = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldUpdateDes(
                valueAux = sexo,
                items = itemsSexo,
                onValueChange = { perfilMascotaViewModel.onSexoChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Sexo",
                supportingText = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }
    }
}

@Composable
fun RowTamanyoPeso(
    perfilMascotaViewModel: PerfilMascotaViewModel,
    editMode: Boolean,
    tamanyo: String,
    itemsTamanyo: List<String>,
    peso: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(90.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldUpdateDes(
                valueAux = tamanyo,
                items = itemsTamanyo,
                onValueChange = {
                    perfilMascotaViewModel.onTamanyoChange(it)
                },
                Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Tamaño",
                supportingText = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            CustomOutlinedTextFieldUpdate(
                valueAux = peso,
                onValueChange = { perfilMascotaViewModel.onPesoChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Peso",
                supportingText = { },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }
    }
}

@Composable
fun RowNombre(
    colorMascota: Color,
    nonbreMascota: String,
    editMode: Boolean,
    perfilMascotaViewModel: PerfilMascotaViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(40.dp)
                .background(colorMascota, RoundedCornerShape(100f))
        )
        Spacer(modifier = Modifier.size(15.dp))
        CustomText(
            text = nonbreMascota, color = Verde, fontSize = 30.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
        Spacer(modifier = Modifier.size(15.dp))
        MyAlertDialogDimiss(show = perfilMascotaViewModel.showDialogDelete ,
            icon = Icons.Rounded.Warning,
            onConfirm = { perfilMascotaViewModel. onDialogDeleteConfirm(navController) },
            onDismiss = { perfilMascotaViewModel.onDialogDeleteDismiss(navController) },
            dialogTitle = "Atención", dialogText = "¿Estás seguro que quieres borrar la mascota?")
        if (editMode) {
            LoginButton(text = "Guardar", 15, 15,) {
                perfilMascotaViewModel.validarCampos(navController, context)
            }
        }

    }
}