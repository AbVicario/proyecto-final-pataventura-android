package com.example.pataventura.ui.composables

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import com.example.pataventura.R
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.example.pataventura.ui.theme.VerdeOliva
import android.graphics.drawable.BitmapDrawable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.example.pataventura.ui.screens.registro.RegistroViewModel


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
fun LoginButton(text: String , paddingStart: Int?, paddingEnd: Int?,  onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingStart?.dp ?: 40.dp, end = paddingEnd?.dp ?: 40.dp)
            .clickable { onClick() }
            .size(width = 180.dp, height = 50.dp)
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
fun MyCustomButton(texto: String, color: Color , onClick: () -> Unit) {

    Column(
        Modifier
            .height(45.dp)
            .width(120.dp)
            .background(color, shape = RoundedCornerShape(10.dp))
            .clickable { onClick()},
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ){
        CustomText(text = texto, color = Color.White,
            fontSize = 20.sp, fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily )
    }

}
@Composable
fun IconButtonImage( registroViewModel: RegistroViewModel

) {
    val context = LocalContext.current

    var selectedImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageBitmap = uri?.let { uri ->
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.use { stream ->
                BitmapFactory.decodeStream(stream)?.asImageBitmap()
            }
        }
    }

    Box(
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .size(80.dp)
            .clip(RoundedCornerShape(150f))
            .background(Verde.copy(alpha = 0.2f))
            .clickable(
                onClick = { launcher.launch("image/*") },
                enabled = true,
                role = Role.Button,

                ),
        contentAlignment = Alignment.Center
    ) {
        if (selectedImageBitmap != null) {
            registroViewModel.onImagenChange(selectedImageBitmap!!)
            Image(
                bitmap = selectedImageBitmap!!,
                contentDescription ="Imagen de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            val icon = R.drawable.icono_persona
            registroViewModel.onImagenChange(painterToImageBitmap(context, icon))
            Image(
                painter = painterResource(icon),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(80.dp).clip(CircleShape).alpha(0.65f)
            )
        }

        /*Icon(
            Icons.Default.Person, "Imagen de perfil",
            modifier = Modifier.size(80.dp),
                tint=(Verde.copy(0.5f))
        )*/
        /*val contentColor = Verde
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)*/
    }
}

@Composable
fun IconButtonImageMascota(
    registroMascotaViewModel: RegistroMascotaViewModel
) {
    val context = LocalContext.current
    var selectedImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageBitmap = uri?.let { uri ->
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.use { stream ->
                BitmapFactory.decodeStream(stream)?.asImageBitmap()
            }
        }
    }

    Box ( modifier = Modifier
        .minimumInteractiveComponentSize()
        .size(85.dp)
        .clip(RoundedCornerShape(100f))
        .background(Verde.copy(alpha = 0.2f))
        .clickable(onClick = {launcher.launch("image/*") }),
        contentAlignment = Alignment.Center
    ){
        if (selectedImageBitmap != null) {
            registroMascotaViewModel.onImagenChange(selectedImageBitmap!!)
            Image(
                bitmap = selectedImageBitmap!!,
                contentDescription ="Imagen de perfil",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            val icon = R.drawable.huella_registro
            registroMascotaViewModel.onImagenChange(painterToImageBitmap(context, icon))
            Image(
                painter = painterResource(icon),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(65.dp)
            )
        }
    }
}


fun painterToImageBitmap(context: Context, @DrawableRes resId: Int): ImageBitmap {
    val drawable = ContextCompat.getDrawable(context, resId) ?: throw IllegalArgumentException("Drawable not found")
    val bitmap = (drawable as BitmapDrawable).bitmap
    return bitmap.asImageBitmap()
}



