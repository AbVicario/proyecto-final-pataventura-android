package com.example.pataventura.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel
import com.example.pataventura.ui.theme.Verde

@Composable
fun ColumnBotonesMascota(
    navController: NavController,
    isIcon: Boolean,
    perfilMascotaViewModel: PerfilMascotaViewModel) {
    Column {
        Column(
            Modifier
                .width(80.dp)
                .fillMaxHeight(0.9f)
                .padding(top = 14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MyboxButton(Icons.Default.Edit, isIcon) {
                perfilMascotaViewModel.onEditModeChange(true)
            }
            MyboxButton(Icons.Default.CalendarMonth, isIcon) {

            }
            MyboxButton(Icons.AutoMirrored.Filled.MenuBook, isIcon) {
                navController.navigate("historialMascota" + "/" + perfilMascotaViewModel.mascota.value!!.idMascota)
            }
            MyboxButton(Icons.Default.DeleteOutline, isIcon) {
                perfilMascotaViewModel.onDelete(navController)
            }
        }
    }
}

@Composable
fun ColumnBotonesUsuario(
    isIcon: Boolean,
    perfilTutorViewModel: PerfilTutorViewModel,
    editMode: Boolean,
    navController: NavController
) {
    var rol = RoleHolder.rol.value.toString().lowercase()
    Column {
        Column(
            Modifier
                .width(80.dp)
                .fillMaxHeight(0.9f)
                .padding(top = 14.dp),
            verticalArrangement = Arrangement.Top
        ) {
            MyboxButton(Icons.Default.PowerSettingsNew, isIcon) {
                perfilTutorViewModel.clearDB(navController)
            }
            MyboxButton(Icons.Default.Edit, isIcon) {
                perfilTutorViewModel.onValueChangeEditMode(true)
            }
            if(rol == "cuidador"){
                MyboxButton(Icons.AutoMirrored.Filled.MenuBook, isIcon) {
                    navController.navigate("historialCuidador")
                }
            }
        }
    }
}

@Composable
fun MyboxButton(icon: ImageVector, isIcon: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier
        .size(40.dp)
        .shadow(3.dp, RoundedCornerShape(100f))
        .background(if (isIcon) Color.White else Verde, RoundedCornerShape(100f))
        //.border(2.dp, Color.White, RoundedCornerShape(100f))
        .clickable { onClick() }) {
        Icon(
            icon, null,
            Modifier.align(Alignment.Center),
            tint = if (!isIcon) Color.White else Verde
        )

    }

    Spacer(modifier = Modifier.height(12.dp))
}