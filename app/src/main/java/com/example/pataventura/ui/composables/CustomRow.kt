package com.example.pataventura.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun RowHistorial(text:String, image: ImageBitmap){
    Row (verticalAlignment = Alignment.CenterVertically){
        Image(
           image, contentDescription = "Imagen mascota",
            Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.size(20.dp))
        CustomText(text = text, color = Verde,
            fontSize = 30.sp, fontWeight = FontWeight.Bold ,
            fontFamily = CustomFontFamily)
    }
}

@Composable
fun RowValoracion(size:Int) {
    Row (Modifier.padding()){
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(size.dp),tint = Verde
        )
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(size.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarRate, contentDescription = null,
            Modifier.size(size.dp),tint = Verde
        )
        Icon(
            Icons.Default.StarHalf, contentDescription = null,
            Modifier.size(size.dp), tint = Verde
        )
        Icon(
            Icons.Default.StarOutline, contentDescription = null,
            Modifier.size(size.dp), tint = Verde
        )
    }
}