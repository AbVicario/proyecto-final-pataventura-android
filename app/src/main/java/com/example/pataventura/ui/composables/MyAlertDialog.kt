package com.example.pataventura.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.ui.theme.Verde

@Composable
fun MyAlertDialog(
    show: Boolean,
    icon: ImageVector,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {
    
    val customButtonColors: ButtonColors = ButtonDefaults.textButtonColors(
        contentColor = Color.White,
        containerColor = Verde
    )
    if(show){
        AlertDialog(onDismissRequest = {  },
            confirmButton = {
                TextButton( colors = customButtonColors,
                    onClick = { onConfirm()}) {
                    Text(text = "Confirmar", fontSize = 20.sp)
                }
            },
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            },
            title = { Text(text = dialogTitle, fontSize = 20.sp) },
            text = { Text(text = dialogText, fontSize = 20.sp) },
            containerColor = Color.White,
            iconContentColor = Verde,
            titleContentColor = Verde,
            textContentColor = Verde
        )
    }
}

@Composable
fun MyAlertDialogDimiss(
    show: Boolean,
    icon: ImageVector,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {

    val customButtonColors: ButtonColors = ButtonDefaults.textButtonColors(
        contentColor = Color.White,
        containerColor = Verde
    )
    if(show){
        AlertDialog(onDismissRequest = {  },
            confirmButton = {
                TextButton( colors = customButtonColors,
                    onClick = { onConfirm()}) {
                    Text(text = "Confirmar", fontSize = 20.sp)
                }
            },
            dismissButton = {
                TextButton(colors = customButtonColors,
                    onClick = { onDismiss() }) {
                    Text(text = "Cancelar", fontSize = 20.sp)
                }
            },
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            },
            title = { Text(text = dialogTitle, fontSize = 20.sp) },
            text = { Text(text = dialogText, fontSize = 20.sp) },
            containerColor = Color.White,
            iconContentColor = Verde,
            titleContentColor = Verde,
            textContentColor = Verde
        )
    }
}