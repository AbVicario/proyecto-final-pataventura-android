package com.example.pataventura.core.navigations

sealed class Destinations(
    val route: String
) {
    object Login : Destinations("login")
    object LoginCliente : Destinations("loginCliente")
    object RegisterOne : Destinations("registerOne")
    object RegisterTwo : Destinations("registerTwo")
    object RegisterMascota : Destinations("registerMascota")
    object Home : Destinations("home")
    object RegistroServicio : Destinations("registroServicio")
    object Contratacion : Destinations("contratacion")
    object PerfilTrabajador : Destinations("perfilTrabajador")
    object Calendario : Destinations("calendario")
    object HistorialMascota : Destinations("historialMascota")
    object HistorialCuidador : Destinations("historialCuidador")
    object Mascotas : Destinations("mascotas")
    object PerfilMascota : Destinations("perfilMascota")
    object PerfilTutor : Destinations("perfilTutor")
    object Servicio : Destinations("servicio")
    object Notificaciones : Destinations("notificaciones")

}