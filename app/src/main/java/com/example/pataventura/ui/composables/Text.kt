package com.example.pataventura.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        fontSize = fontSize,
        modifier = modifier,
    )
}

@Composable
fun EmailNoValidoText(){
    CustomText(text = "Email no valido", color = Color.Red, fontSize = 12.sp,
        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default)
}
@Composable
fun CampoObligatorioText(){
    CustomText(text = "Campo obligatorio", color = Color.Red, fontSize = 12.sp,
        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default)
}


@Composable
fun PassConText(){
    CustomText(text = "Las contraseñas no coinciden", color = Color.Red, fontSize = 12.sp,
        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default)
}

@Composable
fun PrecioValText(){
    CustomText(text = "El precio no es valido", color = Color.Red, fontSize = 12.sp,
        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default)
}