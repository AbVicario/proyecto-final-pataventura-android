package com.example.pataventura.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.theme.Verde

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeaderLogin(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.265f)
            .clip(RoundedCornerShape(bottomEnd = 60.dp))
            .background(Verde)
    ) {
        Image(
            painter = painterResource(id = R.drawable.huella),
            contentDescription = "Huella", Modifier.fillMaxSize()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(Modifier.fillMaxWidth(0.4f)) {
                Image(
                    painter = painterResource(id = R.drawable.gato_login),
                    contentDescription = "Gato",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.nombre),
                contentDescription = "Nombre",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 30.dp)
            )
        }
    }
}