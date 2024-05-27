package com.example.pataventura.core.navigations

import LoginScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.pataventura.ui.screens.mascotas.MascotasScreen
import com.example.pataventura.ui.screens.mascotas.MascotasViewModel
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorScreen
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaScreen
import com.example.pataventura.ui.screens.perfil_mascota.PerfilMascotaViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorScreen
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaScreen
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.screens.registro.RegistroDosScreen
import com.example.pataventura.ui.screens.regisrtroServicio.RegistroServicioScreen
import com.example.pataventura.ui.screens.regisrtroServicio.RegistroServicioViewModel
import com.example.pataventura.ui.screens.registro.RegistroUnoScreen
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.screens.servicio.ServicioScreen
import com.example.pataventura.ui.screens.servicio.ServicioViewModel


@RequiresApi(Build.VERSION_CODES.S)
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
    mascotasViewModel: MascotasViewModel,
    perfilMascotaViewModel: PerfilMascotaViewModel,
    perfilTutorViewModel: PerfilTutorViewModel,
    servicioViewModel: ServicioViewModel

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.LoginCliente.route
        //startDestination = Destinations.RegisterTwo.route
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
            ContratacionScreen(navController, contratacionViewModel, homeViewModel)
        }
        composable(Destinations.PerfilTrabajador.route + "/{id_cuidador}",
            arguments = listOf(navArgument("id_cuidador") { type = NavType.IntType })
        ) {
            val idCuidador = it.arguments?.getInt("id_cuidador")

            if (idCuidador == null) {
                HomeScreen(navController, homeViewModel)

                return@composable
            }

            PerfilTrabajadorScreen(navController, perfilTrabajadorViewModel,homeViewModel)
            perfilTrabajadorViewModel.setTrabajadorId(idCuidador)
            contratacionViewModel.setTrabajadorId(idCuidador)
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
        composable(Destinations.Mascotas.route) {
            MascotasScreen(navController, mascotasViewModel, homeViewModel)
        }
        composable(route = Destinations.PerfilMascota.route + "/{id_mascota}",
            arguments = listOf(navArgument("id_mascota") { type = NavType.IntType })
        ) {
            val idMascota = it.arguments?.getInt("id_mascota")

            if (idMascota == null) {
                MascotasScreen(navController, mascotasViewModel, homeViewModel)

                return@composable
            }
            PerfilMascotaScreen(navController, perfilMascotaViewModel)
            perfilMascotaViewModel.setMascotaId(idMascota)
        }
        composable(Destinations.PerfilTutor.route) {
            PerfilTutorScreen(navController, perfilTutorViewModel)
        }

        composable(Destinations.Servicio.route) {
            ServicioScreen(navController, servicioViewModel, perfilTrabajadorViewModel)
        }

    }
}