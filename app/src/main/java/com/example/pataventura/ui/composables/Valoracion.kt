package com.example.pataventura.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.*
import com.example.pataventura.ui.theme.Verde


@Composable
fun MyValoracionStars(valoracion: Double, sizeStars: Int) {
    Row(){
        var index = 5
        var resultado = valoracion
        while (index > 0) {
            if(resultado >= 1){
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Star",
                    Modifier.size(sizeStars.dp),
                    tint = Verde)
            }else if(valoracion < 1 && valoracion >= 0.5){
                Icon(Icons.AutoMirrored.Filled.StarHalf,
                    contentDescription = "HalfStar",
                    Modifier.size(sizeStars.dp),
                    tint = Verde)
            }else{
                Icon(Icons.Default.StarBorder,
                    contentDescription = "Empty Star",
                    Modifier.size(sizeStars.dp),
                    tint = Verde)
            }
            index--
            resultado --
        }
    }

}
