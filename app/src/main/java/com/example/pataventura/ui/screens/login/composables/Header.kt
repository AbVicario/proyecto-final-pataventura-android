package com.example.pataventura.ui.screens.login.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.theme.Verde

@Composable
fun HeaderLogin(){
    Box(modifier = Modifier
        .fillMaxHeight(0.3f)
        .background(Color.Red)

    ){
        Image(
            painter = painterResource(id = R.drawable.header_log),
            contentDescription = "Header",
        )
    }
}
