package com.example.pataventura.ui.screens.registro.composables

import android.graphics.DrawFilter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfil
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
@Composable
fun HeaderRegistro() {
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(Modifier.fillMaxWidth(0.47f)) {
                Image(
                    painter = painterResource(id = R.drawable.perro_registro),
                    contentDescription = "Perro",
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
    /*val colorsWithWidth = listOf<Pair<Color,Float>>(
        Verde to 70f,
        Color.Black to 30f
    )*/
    //ColoredBare(colorsWithWidth = colorsWithWidth)
}


/*@Composable
fun ColoredBar(colorsWithWidth: List<Pair<Color, Float>>) {
    Canvas(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
    ) {
        val totalHeight = size.height
        var usedHeight = 0f
        colorsWithWidth.forEach { (color, height) ->
            val colorHeight = totalHeight * (height / 100f)
            drawRect(
                color = color,
                topLeft = Offset(usedHeight, 0f),
                size = Size(colorHeight, size.height)
            )
            usedHeight += colorHeight
        }
    }
}*/
/*@Composable
fun ColoredBaree(colorsWithWidth: List<Pair<Color, Float>>) {
    Canvas(
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .fillMaxWidth()
            //.clip(RoundedCornerShape(bottomEnd = 40.dp))
    ) {
        val totalWidth = size.height
        var usedWidth = 0f
        colorsWithWidth.forEachIndexed { index, (color, width) ->
            val colorWidth = totalWidth * (width / 100f)
            if (index == 0) { // Ajusta el índice según sea necesario
                drawRoundRect(
                    cornerRadius = CornerRadius(
                        [topLeft = 0f, 0f}],
                        topRight = 0f,
                        bottomRight = 40f,
                        bottomLeft = 0f
                    ),
                    color = color,
                    topLeft = Offset(0f, usedWidth),
                    size = Size(size.width, colorWidth),

                )
            } else {
                drawRect(
                    color = color,
                    topLeft = Offset(0f, usedWidth),
                    size = Size(size.width, colorWidth)
                )
            }
            usedWidth += colorWidth
        }
    }
}

@Composable
fun ColoredBare(colorsWithWidth: List<Pair<Color, Float>>) {
    Box(modifier = Modifier
        .height(400.dp)
        .fillMaxWidth()
    ) {
        Canvas(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            val totalWidth = size.width
            var usedWidth = 0f
            colorsWithWidth.forEach { (color, width) ->
                val colorWidth = totalWidth * (width / 100f)
                drawRect(
                    color = color,
                    topLeft = Offset(0f, usedWidth),
                    size = Size(size.width, colorWidth)
                )
                usedWidth += colorWidth
            }

        }
        Image(
            painter = painterResource(id = R.drawable.perro_registro),
            contentDescription = "Perro",
            modifier = Modifier.fillMaxHeight()
        )
    }
}*/
