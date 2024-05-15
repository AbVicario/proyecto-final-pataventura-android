package com.example.pataventura.ui.screens.perfilTutor.composables

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.pataventura.ui.composables.ColumnBotonesUsuario
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel

import com.example.pataventura.ui.theme.Verde

@SuppressLint("Range")
@Composable
fun HeaderPerfilTutor(
    editMode: Boolean, image: ImageBitmap?,
    perfilTutorViewModel: PerfilTutorViewModel
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
        /*Column(modifier = Modifier.align(Alignment.TopStart)){
            IconBack()
        }*/
        Box(
            Modifier
                .align(Alignment.TopEnd)
                .width(55.dp)
                .padding(top = 15.dp)
        ) {
            ColumnBotonesUsuario(
                if (image != null) false else true,
                perfilTutorViewModel,
                editMode
            )
        }
    }
}


