package com.example.pataventura.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import kotlinx.coroutines.delay

@Composable
fun MySplash(
    navController: NavController,
    mySplashViewModel: MySplashViewModel
) {
    val nombre: String by mySplashViewModel.nombre.observeAsState("")
    Box(
        Modifier
            .fillMaxSize()
            .background(Verde),
        contentAlignment =  Alignment.Center
    ){
        LaunchedEffect( true ){
            delay(2500)
            navController.popBackStack()
            navController.navigate("home")
        }
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            CustomText(text = "Hola $nombre", color = Color.White,
                fontSize = 50.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(text = "Bienvenid@", color = Color.White,
                fontSize = 50.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = R.drawable.nombre),
                contentDescription = "Nombre App", modifier = Modifier.fillMaxSize())
        }

    }

}