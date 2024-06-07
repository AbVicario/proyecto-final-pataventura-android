package com.example.pataventura.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun ValoracionesScreen(
    navController: NavController,
    historialMascotaViewModel: HistorialMascotaViewModel
) {
    val showDialog by historialMascotaViewModel.showDialog.observeAsState(false)
    var rating by remember { mutableStateOf(1) }

    MyAlertDialogValoraciones(
        navController = navController,
        historialMascotaViewModel = historialMascotaViewModel,
        show = showDialog,
        icon = Icons.Default.ThumbUpOffAlt,
        dialogTitle = {
            CustomText(
                text = "Valoración", color = Verde,
                fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )
        },
        dialogText = {
            Column(horizontalAlignment = Alignment.Start) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Valora tu experiencia", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { rating = i
                                    historialMascotaViewModel.onValoracionChange(rating)}
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    CustomOutlinedTextField(
                        onValueChange = { historialMascotaViewModel.onComentarioChange(it) },
                        Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        enabled = true,
                        readOnly = false,
                        placeholder = "Comentario",
                        supportingText = { },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = false
                    )
                }
            }
        }
    )
}
@Composable
fun MyAlertDialogValoraciones(
    navController: NavController,
    historialMascotaViewModel: HistorialMascotaViewModel,
    show: Boolean,
    icon: ImageVector,
    dialogTitle: @Composable () -> Unit,
    dialogText: @Composable () -> Unit
): Unit {
    val context = LocalContext.current
    val customButtonColors: ButtonColors = ButtonDefaults.textButtonColors(
        contentColor = Color.White,
        containerColor = Verde
    )
    if (show) {
        AlertDialog(
            onDismissRequest = { historialMascotaViewModel.hideDialog() },
            confirmButton = {
                TextButton(colors = customButtonColors,
                    onClick = { historialMascotaViewModel.registerValoracion(context, navController)}) {
                    Text(text = "Valorar", fontSize = 20.sp)
                }
            },
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            },
            title = { dialogTitle() },
            text = { dialogText() },
            containerColor = Color.White,
            iconContentColor = Verde,
            titleContentColor = Verde,
            textContentColor = Verde
        )
    }
}