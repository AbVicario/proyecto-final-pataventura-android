package com.example.pataventura.ui.screens.perfilTutor.composables

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.composables.ColumnBotonesUsuario
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel

import com.example.pataventura.ui.theme.Verde

@SuppressLint("Range")
@Composable
fun HeaderPerfilTutor(
    editMode: Boolean,
    perfilTutorViewModel: PerfilTutorViewModel,
    navController: NavController
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
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Verde, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .clickable { if(editMode) launcher.launch("image/*") }
    ) {

        var imageBitmap = perfilTutorViewModel.imagen.value
        if (selectedImageBitmap != null) {
            perfilTutorViewModel.onValueChangeImage(selectedImageBitmap!!)
            imageBitmap = selectedImageBitmap!!
        }

        Image(
            imageBitmap!!,
            contentDescription = "Imagen perfil",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        )

        Box(
            Modifier
                .align(Alignment.TopEnd)
                .width(55.dp)
                .padding(top = 15.dp)
        ) {
            ColumnBotonesUsuario(false,
                perfilTutorViewModel,
                editMode,
                navController
            )
        }
    }
}


