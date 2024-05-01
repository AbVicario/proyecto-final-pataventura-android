package com.example.pataventura.ui.screens.registro.composables

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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldPass
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.EmailNoValidoText
import com.example.pataventura.ui.composables.IconButtonImage
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyAlertDialog
import com.example.pataventura.ui.composables.PassConText
import com.example.pataventura.ui.composables.PrecioValText
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.registro.RegistroServicioViewModel
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyRegistroDos(registroViewModel: RegistroViewModel , navController: NavController, loginClienteViewModel: LoginClienteViewModel) {
    val nombreEmpty: Boolean by registroViewModel.nombreEmpty.observeAsState(false)
    val apellidosEmpty: Boolean by registroViewModel.apellidosEmpty.observeAsState(false)
    val direccionEmpty: Boolean by registroViewModel.direccionEmpty.observeAsState(false)
    val telefonoEmpty: Boolean by registroViewModel.telefonoEmpty.observeAsState(false)
    val tipo: String by loginClienteViewModel.tipo.observeAsState("")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.silueta_perro_registro),
            contentDescription = "Silueta perro", Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 15.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomText(
                text = "Introduce tus datos",
                color = Verde, fontSize = 20.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButtonImage()
                Spacer(modifier = Modifier.width(10.dp))

                CustomOutlinedTextField(
                    onValueChange = { registroViewModel.onNombreChange(it) },
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    enabled = true,
                    readOnly = false,
                    placeholder = "Nombre",
                    leadingIcon = { Icon(Icons.Default.Person, null) },
                    supportingText = {if(nombreEmpty) CampoObligatorioText() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )

            }

            CustomOutlinedTextField(
                onValueChange = { registroViewModel.onApellidosChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Apellidos",
                leadingIcon = { Icon(Icons.Default.Person, null) },
                supportingText = {if(apellidosEmpty) CampoObligatorioText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            CustomOutlinedTextField(
                onValueChange = { registroViewModel.onPhoneChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Teléfono",
                leadingIcon = { Icon(Icons.Default.Phone, null) },
                supportingText = {if(telefonoEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true
            )

            CustomOutlinedTextField(
                onValueChange = { registroViewModel.onDireccionChange(it) },
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Dirección",
                leadingIcon = { Icon(Icons.Default.Home, null) },
                supportingText = {if(direccionEmpty) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                MyAlertDialog(
                    show = registroViewModel.showDialog,
                    icon = Icons.Default.Error,
                    onConfirm = { registroViewModel.onDialogConfirm(navController) },
                    dialogTitle = "Error",
                    dialogText ="Ha habido un error en el registro. Intentelo mas tarde"
                )
                LoginButton(text = "Siguiente",
                    onClick = { registroViewModel.onRegistroDosButtonClicked(navController, tipo) })
            }
        }
    }
}


@Composable
fun BodyRegistroUno(registroViewModel: RegistroViewModel, navController: NavController ) {
    val emailNoValido: Boolean by registroViewModel.emailNoValido.observeAsState(false)
    val emailEmpty: Boolean by registroViewModel.emailEmpty.observeAsState(false)
    val passConEmpty: Boolean by registroViewModel.passConEmpty.observeAsState(false)
    val passEmpty: Boolean by registroViewModel.passEmpty.observeAsState(false)
    val aliasEmpty: Boolean by registroViewModel.aliasEmpty.observeAsState(false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.silueta_perro_registro),
            contentDescription = "Silueta perro", Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {

            CustomText(
                text = "Introduce tus datos  ",
                color = Verde, fontSize = 20.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
            CustomOutlinedTextField(
                onValueChange = { registroViewModel.onAliasChange(it)},
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Alias",
                leadingIcon = { Icon(Icons.Default.Person, null) },
                trailingIcon = {},
                supportingText = {if(aliasEmpty) CampoObligatorioText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            CustomOutlinedTextField(
                onValueChange = { registroViewModel.onEmailChange(it)},
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Email",
                leadingIcon = { Icon(Icons.Default.Mail, null) },
                trailingIcon = {},
                supportingText = {if(emailEmpty) CampoObligatorioText()
                                 else if(emailNoValido) EmailNoValidoText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            CustomOutlinedTextFieldPass(
                onValueChange = { registroViewModel.onPasswordChange(it)},
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Password",
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                supportingText = {if(passEmpty) CampoObligatorioText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
            CustomOutlinedTextFieldPass(
                onValueChange = {registroViewModel.onPasswordConChange(it)},
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                enabled = true,
                readOnly = false,
                placeholder = "Confirmar",
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                supportingText = {if(passConEmpty) PassConText()},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        Box(
            Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            LoginButton(text = "Siguiente",
                onClick = {registroViewModel.onPressRegistroUno(navController)})
        }
    }
    }
}

@Composable
fun BodyRegistroServicio(registroServicioViewModel: RegistroServicioViewModel,
                         navController: NavController) {
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

            Row (Modifier.fillMaxWidth(),
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
                    supportingText = {if(isServicio) CampoObligatorioText()},
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
                                     else if(isPrecioValido) PrecioValText()},
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
                supportingText = {if(isRango) CampoObligatorioText()},
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
                supportingText = {if(isDescripcion) CampoObligatorioText()},
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
                    dialogText ="Ha habido un error en el registro. Intentelo mas tarde"
                )

                LoginButton(text = "Finalizar",
                    onClick = {registroServicioViewModel.onRegistroServicioButtonClicked(navController)})
            }
        }
    }
}



