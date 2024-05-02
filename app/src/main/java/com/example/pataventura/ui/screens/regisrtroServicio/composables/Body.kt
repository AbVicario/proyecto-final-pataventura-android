package com.example.pataventura.ui.screens.regisrtroServicio.composables

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
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyAlertDialog
import com.example.pataventura.ui.composables.PrecioValText
import com.example.pataventura.ui.screens.regisrtroServicio.RegistroServicioViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyRegistroServicio(registroServicioViewModel: RegistroServicioViewModel,
                         navController: NavController
) {
    val listaServicios = listOf("Paseo", "Guardería")
    val isDescripcion: Boolean by registroServicioViewModel.isDescripcion.observeAsState(false)
    val isRango: Boolean by registroServicioViewModel.isRango.observeAsState(false)
    val isServicio: Boolean
            by registroServicioViewModel.isServicioSeleccionado.observeAsState(false)
    val isPrecio: Boolean by registroServicioViewModel.isPrecio.observeAsState(false)
    val isPrecioValido: Boolean by registroServicioViewModel.isPrecioValido.observeAsState(false)
    val servicio: String by registroServicioViewModel.servicio.observeAsState("")
    val rango: String by registroServicioViewModel.rango.observeAsState("")


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
            horizontalAlignment = Alignment.CenterHorizontally) {

            CustomText(text = "Introduce los datos de tu servicio",
                color = Verde , fontSize = 20.sp ,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily )

            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){

                CustomOutlinedTextFieldDes(
                    text = servicio,
                    items = listaServicios,
                    onValueChange = {registroServicioViewModel.onChangeServicio(it)},
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(100.dp),
                    enabled = true,
                    readOnly = true,
                    placeholder = "Servicio",
                    supportingText = {if(isServicio) CampoObligatorioText() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(10.dp))
                CustomOutlinedTextField(
                    onValueChange = {registroServicioViewModel.onChangePrecio(it)},
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Precio",
                    supportingText = {if(isPrecio) CampoObligatorioText()
                    else if(isPrecioValido) PrecioValText()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }

            CustomOutlinedTextFieldDes(
                text = rango,
                items = registroServicioViewModel.listaRango,
                onValueChange = {registroServicioViewModel.onChangeRango(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = true,
                placeholder = "Radio de acción",
                supportingText = {if(isRango) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            CustomOutlinedTextField(
                onValueChange = {registroServicioViewModel.onChangeDescripcion(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Descripción",
                supportingText = {if(isDescripcion) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = false
            )

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                MyAlertDialog(
                    show = registroServicioViewModel.showDialog,
                    icon = Icons.Default.Error,
                    onConfirm = { registroServicioViewModel.onDialogConfirm(navController) },
                    dialogTitle = "Error",
                    dialogText ="Ha habido un error al completar el registro. Recuerde registrar un servicio" +
                            "para que su usuario sea visible para otros usuarios de la aplicación." +
                            " Intentelo mas tarde"
                )

                LoginButton(text = "Finalizar",
                    onClick = {registroServicioViewModel.onPressRegistroServicioLaunch(navController)})
            }
        }
    }
}