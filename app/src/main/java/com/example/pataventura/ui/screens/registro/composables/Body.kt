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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextDescripcionServicio
import com.example.pataventura.ui.composables.CustomOutlinedTextEmail
import com.example.pataventura.ui.composables.CustomOutlinedTextHome
import com.example.pataventura.ui.composables.CustomOutlinedTextPass
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfil
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascota
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascotaDesplegable
import com.example.pataventura.ui.composables.CustomOutlinedTextPhone
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.IconButtonImage
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.screens.registro.RegistroServicioViewModel
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import javax.net.ssl.HostnameVerifier

@Composable
fun BodyRegistroDos(registroViewModel: RegistroViewModel , navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.silueta_perro_registro),
            contentDescription = "Silueta gato", Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 15.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

        CustomText(text = "Introduce tus datos",
            color = Verde , fontSize = 20.sp ,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily )

        Row (
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            IconButtonImage()
            Spacer(modifier = Modifier.width(10.dp))

            CustomOutlinedTextPerfil(placeholder = "Nombre:", false){
                registroViewModel.onNombreChange(
                    it
                )
            }

        }
        CustomOutlinedTextPerfil(placeholder = "Apellidos:", false){
            registroViewModel.onApellidosChange(
                it
            )
        }

        CustomOutlinedTextPhone(placeholder = "Teléfono" , false){
            registroViewModel.onPhoneChange(
                it
            )
        }

        CustomOutlinedTextHome(placeholder = "Dirección", true ){
            registroViewModel.onHomeChange(
                it
            )
        }

        Box(
            Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            LoginButton(text = "Suiguiente",
                onClick = {registroViewModel.onPressRegistroDos(navController)})
        }
    }
    }
}

@Composable
fun BodyRegistroUno(registroViewModel: RegistroViewModel, navController: NavController ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.silueta_perro_registro),
            contentDescription = "Silueta gato", Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 15.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {

        CustomText(
            text = "Introduce los datos de tu servicio",
            color = Verde, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )

        //Icon()
        CustomOutlinedTextEmail(placeholder = "Email:", false){
            registroViewModel.onEmailChange(
                it
            )
        }

        CustomOutlinedTextPass(placeholder = "Contraseña:" , false ){
            registroViewModel.onPasswordChange(
                it
            )
        }

        CustomOutlinedTextPass(placeholder = "Repetir contraseña", false ){
            registroViewModel.onRepetirChange(
                it
            )
        }
        Box(
            Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            LoginButton(text = "Finalizar",
                onClick = {registroViewModel.onPressRegistroUno(navController)})
        }

    }
    }
}

@Composable
fun BodyRegistroServicio(registroServicioViewModel: RegistroServicioViewModel , navController: NavController) {
    var listServicios = listOf("Paseo", "Guardería")
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

            CustomOutlinedTextPerfilMascotaDesplegable(
                items = listServicios,
                placeholder = "Tipo de servicio:",
                keyboardType = KeyboardType.Text,
                opcional = false,
                onTextFieldChange = {},
                onItemSelected = {}
            )

            CustomOutlinedTextPerfilMascota(
                singleLine = true,
                placeholder = "Precio",
                keyboardType = KeyboardType.Number,
                opcional = false,
                onTextFieldChange = {}
            )

            CustomOutlinedTextDescripcionServicio(
                singleLine = false,
                placeholder = "Descripción",
                keyboardType = KeyboardType.Text,
                opcional = false,
                onTextFieldChange ={}
            )

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                LoginButton(text = "Finalizar",
                    onClick = {registroServicioViewModel.onPressRegistroServicio(navController)})
            }
        }
    }
}



