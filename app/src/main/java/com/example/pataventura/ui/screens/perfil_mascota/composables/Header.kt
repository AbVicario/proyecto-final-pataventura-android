package com.example.pataventura.ui.screens.perfil_mascota.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MarkunreadMailbox
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.IconBack
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@SuppressLint("Range")
@Composable
fun HeaderPerfilMascota(){
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Verde, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
    ){

        Image(painter = painterResource(id = R.drawable.imagen_tyrion),
            contentDescription = "Imagen Mascota",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
                .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)))
        Column(modifier = Modifier.align(Alignment.TopStart)){
            IconBack()
        }
        Box(Modifier.align(Alignment.TopEnd).width(55.dp)){
            ColumnBotones()
        }
    }

}

@Composable
fun ColumnBotones() {
    Column {
        Column (Modifier.width(80.dp)
            .fillMaxHeight(0.9f)
            .padding(top=14.dp),
            verticalArrangement = Arrangement.SpaceBetween){
                MyboxButton(Icons.Default.Edit)
                MyboxButton(Icons.Default.CalendarMonth)
                MyboxButton(Icons.AutoMirrored.Filled.MenuBook)
                MyboxButton(Icons.Default.DeleteOutline)
        }
    }
}

@Composable
fun MyboxButton(icon: ImageVector) {
    Box(modifier = Modifier
        .size(40.dp)
        .shadow(3.dp, RoundedCornerShape(100f))
        .background(Verde, RoundedCornerShape(100f))
        //.border(2.dp, Color.White, RoundedCornerShape(100f))
        .clickable {  }){
            Icon(icon, null,
                Modifier.align(Alignment.Center),
                tint = Color.White)

    }
}

