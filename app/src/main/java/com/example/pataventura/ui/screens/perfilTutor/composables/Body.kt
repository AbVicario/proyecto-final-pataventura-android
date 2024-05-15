package com.example.pataventura.ui.screens.perfilTutor.composables

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.composables.CampoObligatorioText
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldUpdate
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.EmailNoValidoText
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.composables.MyAlertDialog
import com.example.pataventura.ui.composables.RowValoracion
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import kotlinx.coroutines.launch

@Composable
fun BodyPerfilTutor(
    editMode: Boolean,
    image: ImageBitmap?,
    perfilTutorViewModel: PerfilTutorViewModel,
    navController: NavController,

    ) {

    val lifecycleScope = rememberCoroutineScope()
    val name by perfilTutorViewModel.name.observeAsState("")
    val alias by perfilTutorViewModel.alias.observeAsState("")
    val phone by perfilTutorViewModel.phone.observeAsState("")
    val email by perfilTutorViewModel.email.observeAsState("")
    val address by perfilTutorViewModel.address.observeAsState("")

    val isAlias by perfilTutorViewModel.isAlias.observeAsState()
    val isPhone by perfilTutorViewModel.isPhone.observeAsState()
    val isEmail by perfilTutorViewModel.isEmail.observeAsState()
    val isAddress by perfilTutorViewModel.isAddress.observeAsState()
    val isNotEmail by perfilTutorViewModel.isNotEmail.observeAsState()

   Box() {
        Image(
            painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize()
        )
        LazyColumn(modifier = Modifier.padding(bottom = 40.dp)) {
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ColumnNombre(name)
                        if (RoleHolder.rol.value.toString().lowercase() == "cuidador") {
                            ColumnValoracion()
                        }
                    }

                    Spacer(modifier = Modifier.size(10.dp))
                    ColumnCaracteristicas(
                        perfilTutorViewModel, editMode, alias, phone, email, address,
                        isAlias, isPhone, isEmail, isAddress, isNotEmail
                    )
                    if (editMode) {
                        MyAlertDialog(
                            show = perfilTutorViewModel.showDialog,
                            icon = Icons.Default.Error,
                            onConfirm = { perfilTutorViewModel.onDialogConfirm(navController) },
                            dialogTitle = "Error",
                            dialogText = "Ha habido un error al actualizar. Intentelo mas tarde"
                        )
                        LoginButton(text = "Guardar") {
                            perfilTutorViewModel.onValueChangeEditMode(false)

                                perfilTutorViewModel.validarCampos(navController)

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnValoracion() {
    Column(Modifier.fillMaxHeight()) {
        RowValoracion(20)
        Box(Modifier.clickable {

        }
        ) {
            CustomText(
                text = "Ver", color = Color.Black,
                fontSize = 14.sp, fontWeight = FontWeight.Bold,
                fontFamily = CustomFontFamily
            )
        }

    }
}

@Composable
fun ColumnNombre(name: String?) {
    val rol = RoleHolder.rol.value.toString().lowercase()
    Column(horizontalAlignment = Alignment.Start) {
        CustomText(
            text = name ?: "", color = Verde, fontSize = 30.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
        CustomText(
            text = if (rol == "tutor") "Tutor" else "Cuidador",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily
        )
    }
}

@Composable
fun ColumnCaracteristicas(
    perfilTutorViewModel: PerfilTutorViewModel,
    editMode: Boolean,
    alias: String?,
    phone: String?,
    email: String?,
    address: String?,
    isAlias: Boolean?,
    isPhone: Boolean?,
    isEmail: Boolean?,
    isAddress: Boolean?,
    isNotEmail: Boolean?
) {

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        CustomOutlinedTextFieldUpdate(
            valueAux = alias,
            onValueChange = { perfilTutorViewModel.onValueChangeAlias(it) },
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = editMode,
            readOnly = !editMode,
            placeholder = "Alias",
            leadingIcon = { Icon(Icons.Default.Person, null) },
            trailingIcon = {},
            supportingText = { if (isAlias != null && !isAlias) CampoObligatorioText() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )

        Spacer(modifier = Modifier.size(14.dp))
        CustomOutlinedTextFieldUpdate(
            valueAux = phone,
            onValueChange = { perfilTutorViewModel.onValueChangePhone(it) },
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = editMode,
            readOnly = !editMode,
            placeholder = "Teléfono",
            supportingText = { if (isPhone != null && !isPhone) CampoObligatorioText() },
            leadingIcon = { Icon(Icons.Default.Phone, null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.size(14.dp))
        CustomOutlinedTextFieldUpdate(
            valueAux = email,
            onValueChange = { perfilTutorViewModel.onValueChangeEmail(it) },
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = editMode,
            readOnly = !editMode,
            placeholder = "Email",
            leadingIcon = { Icon(Icons.Default.Email, null) },
            supportingText = {
                if (isEmail != null && !isEmail) CampoObligatorioText()
                else if (isNotEmail != null && !isNotEmail) EmailNoValidoText()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.size(14.dp))
        CustomOutlinedTextFieldUpdate(
            valueAux = address,
            onValueChange = { perfilTutorViewModel.onValueChangeAddress(it) },
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = editMode,
            readOnly = !editMode,
            placeholder = "Dirección",
            leadingIcon = { Icon(Icons.Default.Home, null) },
            supportingText = { if (isAddress != null && !isAddress) CampoObligatorioText() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
    }
}



