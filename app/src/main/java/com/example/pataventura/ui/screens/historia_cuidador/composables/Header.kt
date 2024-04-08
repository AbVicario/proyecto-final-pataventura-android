package com.example.pataventura.ui.screens.historia_cuidador.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.IconBack
import com.example.pataventura.ui.theme.Verde

@Composable
fun HeaderHistorialCuidador() {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .background(Color.Transparent)
    ) {

        HeaderBox()
        Image(
            painter = painterResource(id = R.drawable.nombre),
            contentDescription = "nombre aplicación",
            Modifier.width(260.dp)
                .height(75.dp)
                .padding(start=60.dp)
        )
        Row(
            Modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconBack()
            Image(
                painter = painterResource(id = R.drawable.imagen_header_home),
                contentDescription = "Dos perros y un gato",
                Modifier
                    .fillMaxSize(0.71f).align(Alignment.Bottom).padding()
            )
        }
    }
}


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