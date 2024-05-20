package com.example.pataventura.ui.screens.home.composables

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HeaderHome(homeViewModel: HomeViewModel){
    val nombre: String by homeViewModel.nombre.observeAsState("")
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .background(Color.Transparent)
    ){

        HeaderBox()

        Image(painter = painterResource(id = R.drawable.imagen_header_home),
            contentDescription = "Dos perros y un gatoo",
            Modifier.align(Alignment.BottomEnd).fillMaxSize(0.71f))

        CustomText(text = "¡Hola $nombre! ", color = Color.White , fontSize = 30.sp,
            fontWeight = FontWeight.Bold , fontFamily = CustomFontFamily,
            Modifier.padding(start = 18.dp, top=15.dp))
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeaderBox(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .clip(RoundedCornerShape(bottomEnd = 40.dp))
            .background(Verde)
    ) {
        Image(
            painter = painterResource(id = R.drawable.huella),
            contentDescription = "Huella", Modifier.fillMaxSize()
        )

    }
}