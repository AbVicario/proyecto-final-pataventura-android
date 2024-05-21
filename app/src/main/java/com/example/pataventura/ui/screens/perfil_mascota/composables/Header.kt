package com.example.pataventura.ui.screens.perfil_mascota.composables

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.pataventura.ui.composables.ColumnBotonesMascota
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel
import com.example.pataventura.ui.theme.Verde
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.asImageBitmap

@SuppressLint("Range")
@Composable
fun HeaderPerfilMascota(perfilMascotaViewModel: PerfilMascotaViewModel, editMode: Boolean) {
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

        var imageBitmap = perfilMascotaViewModel.imagen.value
        if (selectedImageBitmap != null) {
            perfilMascotaViewModel.onImagenChange(selectedImageBitmap!!)
            imageBitmap = selectedImageBitmap!!
        }

        Image(
            imageBitmap!!,
            contentDescription = "Imagen Mascota",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        )

        Box(
            Modifier
                .align(Alignment.TopEnd)
                .width(55.dp)) {
            val isIcon = imageBitmap.width > 513
            ColumnBotonesMascota(isIcon , perfilMascotaViewModel)
        }
    }

}



