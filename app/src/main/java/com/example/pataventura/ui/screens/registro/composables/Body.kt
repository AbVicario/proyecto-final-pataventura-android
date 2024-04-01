package com.example.pataventura.ui.screens.registro.composables

import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextEmail
import com.example.pataventura.ui.composables.CustomOutlinedTextHome
import com.example.pataventura.ui.composables.CustomOutlinedTextPass
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfil
import com.example.pataventura.ui.composables.CustomOutlinedTextPhone
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.IconButtonImage
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import javax.net.ssl.HostnameVerifier

@Composable
fun BodyRegistroDos(registroViewModel: RegistroViewModel) {
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
            .padding( top = 15.dp, bottom = 10.dp),
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

            CustomOutlinedTextPerfil(placeholder = "Nombre:"){
                registroViewModel.onNombreChange(
                    it
                )
            }

        }
        CustomOutlinedTextPerfil(placeholder = "Apellidos:"){
            registroViewModel.onApellidosChange(
                it
            )
        }

        CustomOutlinedTextPhone(placeholder = "Teléfono" ){
            registroViewModel.onPhoneChange(
                it
            )
        }

        CustomOutlinedTextHome(placeholder = "Dirección" ){
            registroViewModel.onHomeChange(
                it
            )
        }

        Box(
            Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            LoginButton(text = "Suiguiente")
        }
    }
    }
}

@Composable
fun BodyRegistroUno(registroViewModel: RegistroViewModel) {
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
                .padding( top = 15.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {

        CustomText(
            text = "Introduce tus datos",
            color = Verde, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
        )
        CustomOutlinedTextPerfil(placeholder = "Alias:"){
            registroViewModel.onAliasChange(
                it
            )
        }

        //Icon()
        CustomOutlinedTextEmail(placeholder = "Email:"){
            registroViewModel.onEmailChange(
                it
            )
        }

        CustomOutlinedTextPass(placeholder = "Contraseña:" ){
            registroViewModel.onPasswordChange(
                it
            )
        }

        CustomOutlinedTextPass(placeholder = "Repetir contraseña" ){
            registroViewModel.onRepetirChange(
                it
            )
        }
        Box(
            Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            LoginButton(text = "Suiguiente")
        }

    }
    }
}