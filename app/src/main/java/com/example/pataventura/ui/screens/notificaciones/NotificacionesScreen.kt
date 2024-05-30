package com.example.pataventura.ui.screens.notificaciones

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.di.NotificacionSize
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.composables.navegacionButtonBar
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.screens.notificaciones.composables.BodyNotificaciones
import com.example.pataventura.ui.screens.notificaciones.composables.HeaderNotificaciones

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificacionesScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    notificacionesViewModel: NotificacionesViewModel
) {
    var selectedIcon by remember { mutableStateOf(Icons.Default.NotificationsNone) }
    LaunchedEffect(Unit) {
        NotificacionSize.setNotificacionSize(0)
    }
    Scaffold(bottomBar = {
        BottomBar(selectedIcon, navController) { icon ->
            selectedIcon = icon
            navegacionButtonBar(icon, navController, RoleHolder.rol.value.toString().lowercase())
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderNotificaciones()
            Spacer(modifier = Modifier.size(10.dp))
            BodyNotificaciones(notificacionesViewModel, loginViewModel, navController)

        }
    }

}