package com.example.pataventura.core.navigations

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pataventura.ui.screens.calendario.CalendarioScreen
import com.example.pataventura.ui.screens.calendario.CalendarioViewModel
import com.example.pataventura.ui.screens.contratacion.ContratacionScreen
import com.example.pataventura.ui.screens.contratacion.ContratacionViewModel
import com.example.pataventura.ui.screens.historia_mascota.HistorialCuidadorScreen
import com.example.pataventura.ui.screens.historia_mascota.HistorialCuidadorViewModel
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaScreen
import com.example.pataventura.ui.screens.historia_mascota.HistorialMascotaViewModel
import com.example.pataventura.ui.screens.home.HomeScreen
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.login.LoginViewModel
import com.example.pataventura.ui.screens.loginCliente.LoginClienteScreen
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorScreen
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel
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
    registroServicioViewModel: RegistroServicioViewModel,
    contratacionViewModel: ContratacionViewModel,
    perfilTrabajadorViewModel: PerfilTrabajadorViewModel,
    calendarioViewModel: CalendarioViewModel,
    historialMascotaViewModel: HistorialMascotaViewModel,
    historialCuidadorViewModel: HistorialCuidadorViewModel,

    ) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.HistorialCuidador.route
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
        composable(Destinations.Contratacion.route) {
            ContratacionScreen(navController, contratacionViewModel)
        }
        composable(Destinations.PerfilTrabajador.route) {
            PerfilTrabajadorScreen(navController, perfilTrabajadorViewModel)
        }
        composable(Destinations.Calendario.route) {
            CalendarioScreen(navController, calendarioViewModel)
        }
        composable(Destinations.HistorialMascota.route) {
            HistorialMascotaScreen(navController, historialMascotaViewModel)
        }
        composable(Destinations.HistorialCuidador.route) {
            HistorialCuidadorScreen(navController, historialCuidadorViewModel)
        }

    }
}