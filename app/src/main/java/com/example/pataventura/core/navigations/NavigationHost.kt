package com.example.pataventura.core.navigations

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pataventura.ui.screens.home.HomeScreen
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.screens.loginCliente.LoginClienteScreen
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaScreen
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.screens.registro.RegistroDosScreen
import com.example.pataventura.ui.screens.registro.RegistroServicioScreen
import com.example.pataventura.ui.screens.registro.RegistroServicioViewModel
import com.example.pataventura.ui.screens.registro.RegistroUnoScreen
import com.example.pataventura.ui.screens.registro.RegistroViewModel

@Composable
fun NavigationHost(
    registerViewModel: RegistroViewModel,
    registerMascotaViewModel: RegistroMascotaViewModel,
    loginViewModel: LoginViewModel,
    loginClienteViewModel: LoginClienteViewModel,
    homeViewModel: HomeViewModel,
    registroServicioViewModel: RegistroServicioViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.RegistroServicio.route
    ) {
        composable(Destinations.Login.route) {
            LoginScreen(navController, loginViewModel)
        }
        composable(Destinations.LoginCliente.route) {
            LoginClienteScreen(navController, loginClienteViewModel)
        }
        composable(Destinations.RegisterOne.route) {
            RegistroUnoScreen(navController, registerViewModel)
        }
        composable(Destinations.RegisterTwo.route) {
            RegistroDosScreen(navController, registerViewModel)
        }
        composable(Destinations.RegisterMascota.route) {
            RegistroMascotaScreen(navController, registerMascotaViewModel)
        }
        composable(Destinations.Home.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(Destinations.RegistroServicio.route) {
            RegistroServicioScreen(navController, registroServicioViewModel)
        }

    }
}