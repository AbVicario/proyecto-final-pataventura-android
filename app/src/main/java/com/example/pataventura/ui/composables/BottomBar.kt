package com.example.pataventura.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.di.IdCuidador
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.theme.Verde

@Composable
fun BottomBar(
    selectedIcon: ImageVector,
    navController: NavController,
    onIconSelected: (ImageVector) -> Unit

) {
    val rol = RoleHolder.rol.value.toString().lowercase()
    val listaIconos: List<Pair<ImageVector, Boolean>> = if (rol == "cuidador") {
        listOf(
            Icons.Default.Home to (Icons.Default.Home == selectedIcon),
            Icons.Default.CalendarMonth to (Icons.Default.CalendarMonth == selectedIcon),
            Icons.Default.Badge to (Icons.Default.Badge == selectedIcon),
            Icons.Default.NotificationsNone to (Icons.Default.NotificationsNone == selectedIcon),
            Icons.Default.Person to (Icons.Default.Person == selectedIcon)
        )
    } else {
        listOf(
            Icons.Default.Home to (Icons.Default.Home == selectedIcon),
            Icons.Default.CalendarMonth to (Icons.Default.CalendarMonth == selectedIcon),
            Icons.Default.Pets to (Icons.Default.Pets == selectedIcon),
            Icons.Default.NotificationsNone to (Icons.Default.NotificationsNone == selectedIcon),
            Icons.Default.Person to (Icons.Default.Person == selectedIcon)
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Color.Transparent)
    ) {
        Box(
            Modifier
                .background(Verde)
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        )

        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listaIconos.forEach { (icon, isSelected) ->
                if (isSelected) {
                    MyIconButtonSelected(
                        icon, navController, rol
                    )
                } else {
                    MyIconButton(icon = icon, onClick = { onIconSelected(icon) })
                }
            }
        }
    }
}


@Composable
fun MyIconButton(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(Verde)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(top = 11.dp)
        )
    }
}

@Composable
fun MyIconButtonSelected(
    icon: ImageVector,
    navController: NavController,
    rol: String
) {
    Column(

    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(45.dp)
        ) {
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
                    .shadow(6.dp, RoundedCornerShape(100f), clip = true)
                    .align(Alignment.TopStart)
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(Verde, RoundedCornerShape(100f))
                        .align(Alignment.Center)
                        .clickable { navegacionButtonBar(icon, navController, rol) }
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

fun navegacionButtonBar(
    icon: ImageVector,
    navController: NavController,
    rol: String
) {
    val idCuidador = IdCuidador.idCuidador.value
    when (icon.name) {
        Icons.Default.Home.name -> if (rol == "tutor") navController.navigate("home") else
            navController.navigate("perfilTrabajador/${idCuidador}")

        Icons.Default.CalendarMonth.name -> navController.navigate("calendario")
        Icons.Default.Pets.name -> navController.navigate("mascotas")
        Icons.Default.NotificationsNone.name -> navController.navigate("home")
        Icons.Default.Person.name -> navController.navigate("perfilTutor")
        Icons.Default.Badge.name -> navController.navigate("servicio")
    }
}