package com.example.pataventura.ui.screens.perfilTutor.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.IconBack
import com.example.pataventura.ui.screens.perfil_mascota.composables.ColumnBotones
import com.example.pataventura.ui.screens.perfil_mascota.composables.MyboxButton
import com.example.pataventura.ui.theme.Verde
@SuppressLint("Range")
@Composable
fun HeaderPerfilTutor(){
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Verde, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
    ){
        Image(painter = painterResource(id = R.drawable.perfil_mujer),
            contentDescription = "Imagen perfil",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
                .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)))
        /*Column(modifier = Modifier.align(Alignment.TopStart)){
            IconBack()
        }*/
        Box(Modifier.align(Alignment.TopEnd).width(55.dp).padding(top = 15.dp)){
            MyboxButton(Icons.Default.Edit)
            //ColumnBotones()
        }
    }
}