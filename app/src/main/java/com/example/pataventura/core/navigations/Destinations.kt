package com.example.pataventura.core.navigations

 sealed class Destinations (
    val route: String
) {
    object Login : Destinations("login")
    object LoginCliente : Destinations("loginCliente")
    object RegisterOne : Destinations("registerOne")
    object RegisterTwo : Destinations("registerTwo")
    object RegisterMascota : Destinations("registerMascota")
    object Home : Destinations("home")
    object RegistroServicio : Destinations("servicio")


}