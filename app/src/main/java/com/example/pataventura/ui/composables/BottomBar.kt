package com.example.pataventura.ui.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.pataventura.ui.theme.Verde
import okhttp3.internal.wait

@Composable
fun BottomBar(){
    val listaIconos: List<Pair<ImageVector, Boolean>> = listOf(
        Icons.Default.Home to true,
        Icons.Default.CalendarMonth to false,
        Icons.Default.Pets to false,
        Icons.Default.NotificationsNone to false,
        Icons.Default.Person to false
    )
    Box(
        Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Color.Transparent)){
        Box(
            Modifier
                .background(Verde)
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart)){

        }
        Row (
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            for (item in listaIconos){
                if(item.second){
                    MyIconButtonSelected(icon = item.first)
                }else{
                    MyIconButton(icon = item.first)
                }

            }
        }
    }
}

@Composable
fun MyIconButton(icon: ImageVector){
    Box(modifier = Modifier
        .background(Verde)
        .clickable { }
    ){
        Icon(icon, null,Modifier.padding(top = 11.dp), tint=Color.White, )
    }
}

@Composable
fun MyIconButtonSelected(icon: ImageVector){
    Column(modifier = Modifier
        .clickable { },
    ){
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(45.dp)
        ){
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Verde)
                    .align(Alignment.BottomStart)
            )
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .border(1.dp, Color.Gray.copy(0.4f), RoundedCornerShape(100f))
                    .shadow(6.dp, RoundedCornerShape(100f), clip = true) // Agregar sombra al contenedor del círculo
                    .align(Alignment.TopStart)
            ){
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(Verde, RoundedCornerShape(100f))
                        .align(Alignment.Center)
                ){
                    Icon(
                        icon,
                        null,
                        Modifier.align(Alignment.Center),
                        tint = Color.White
                    )
                }
            }
        }


    }
}