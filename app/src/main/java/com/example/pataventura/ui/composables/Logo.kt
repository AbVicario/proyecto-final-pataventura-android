package com.example.pataventura.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R

@Composable
fun ImageLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        modifier = Modifier.size(300.dp)
    )

}