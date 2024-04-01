package com.example.pataventura.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

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
        modifier = modifier
    )
}