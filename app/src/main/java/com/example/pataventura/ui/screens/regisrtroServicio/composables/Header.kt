package com.example.pataventura.ui.screens.regisrtroServicio.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.theme.Verde

@Composable
fun HeaderRegistroServicio() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .clip(RoundedCornerShape(bottomEnd = 40.dp))
            .background(Verde)
    ) {
        Image(
            painter = painterResource(id = R.drawable.huella),
            contentDescription = "Huella", Modifier.fillMaxSize()
        )
        Box(
            //horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(Modifier.padding(top=10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.nombre),
                    contentDescription = "Nombre",
                    modifier = Modifier
                        .fillMaxSize(0.6f),
                    Alignment.TopStart
                )
            }

            Box(
                Modifier.fillMaxSize(),
                Alignment.BottomEnd
            ) {

                Image(
                    painter = painterResource(id = R.drawable.gato_perro_header),
                    contentDescription = "Perro",
                    modifier = Modifier.fillMaxSize(0.8f),
                    Alignment.BottomEnd
                )

            }

        }
    }
}