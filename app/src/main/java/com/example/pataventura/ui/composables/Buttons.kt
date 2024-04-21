package com.example.pataventura.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.ui.theme.MyDarkColorScheme
import com.example.pataventura.ui.theme.MyLightColorScheme
import com.example.pataventura.ui.theme.Tierra
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.example.pataventura.ui.theme.Verde30
import com.example.pataventura.ui.theme.Verde55
import com.example.pataventura.ui.theme.VerdeOliva


@Composable
fun CustomButtonStyle(): ButtonColors {
    val colorScheme = if (isSystemInDarkTheme()) MyDarkColorScheme else MyLightColorScheme
    return ButtonDefaults.buttonColors(
        containerColor = colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = colorScheme.secondary,
        disabledContentColor = colorScheme.tertiary,
    )
}

@Composable
fun LoginButton(text: String ,  onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .clickable { onClick() }
            .size(width = 200.dp, height = 60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(VerdeOliva),
        Alignment.Center,
    ) {
        Text(text = text, fontSize = 28.sp, fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily, color = Color.White)
    }
}

@Composable
fun InformationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    information: String
) {

    var showInfoDialog by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            showInfoDialog = !showInfoDialog
            onClick()
        },
        modifier = modifier
    ) {
        Icon(
            Icons.Default.Info,
            contentDescription = "Botón de información"
        )
    }

    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = { Text(text = "Información adicional") },
            text = {
                Text(
                    text = information,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            },
            confirmButton = {
                Button(
                    onClick = { showInfoDialog = false },
                    colors = ButtonDefaults.buttonColors(Tierra)
                ) {
                    Text(text = "Cerrar")
                }
            }
        )
    }
}
@Composable
fun MyCustomButton(texto: String, color: Color) {

    Column(
        Modifier
            .height(45.dp)
            .width(120.dp)
            .background(color, shape = RoundedCornerShape(10.dp))
            .clickable { },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ){
        CustomText(text = texto, color = Color.White,
            fontSize = 20.sp, fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily )
    }

}
@Composable
fun IconButtonImage(

) {
    Box(
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .size(80.dp)
            .clip(RoundedCornerShape(150f))
            .background(Verde.copy(alpha = 0.2f))
            .clickable(
                onClick = {  },
                enabled = true,
                role = Role.Button,

            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.Person, "Imagen de perfil",
            modifier = Modifier.size(80.dp),
                tint=(Verde.copy(0.5f))
        )
        /*val contentColor = Verde
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)*/
    }
}

@Composable
fun IconButtonImageMascota(

) {
    Box(
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .size(85.dp)
            .clip(RoundedCornerShape(100f))
            .background(Verde.copy(alpha = 0.2f))
            .clickable(
                onClick = {  },
                enabled = true,
                role = Role.Button,

                ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.Pets, "Imagen de perfil",
            modifier = Modifier.size(80.dp),
            tint=(Verde.copy(0.5f))
        )
        /*val contentColor = Verde
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)*/
    }
}



