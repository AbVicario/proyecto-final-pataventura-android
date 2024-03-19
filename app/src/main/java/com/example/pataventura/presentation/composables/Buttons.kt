package com.example.pataventura.presentation.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.ui.theme.MyDarkColorScheme
import com.example.pataventura.ui.theme.MyLightColorScheme


@Composable
fun CustomButtonStyle(): ButtonColors {
    val colorScheme = if (isSystemInDarkTheme()) MyDarkColorScheme else MyLightColorScheme

    return ButtonDefaults.buttonColors(
        containerColor = colorScheme.secondary,
        contentColor = Color.White,
        disabledContainerColor = colorScheme.primary,
        disabledContentColor = colorScheme.tertiary
    )
}

@Composable
fun LoginButton(text: String){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 20.dp),
        onClick = { },
        colors = CustomButtonStyle(),
    ) {
        Text(text= text, fontSize =  20.sp)
    }
}
