package com.example.pataventura.ui.screens.servicio.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.theme.Verde


@SuppressLint("Range")
@Composable
fun HeaderServicio(
    image: ImageBitmap?
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Verde, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
    ) {

        if (image != null) {
            Image(
                bitmap = image,
                contentDescription = "Imagen perfil",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
        } else {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icono de persona",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                tint = Color.White
            )
        }
    }
}