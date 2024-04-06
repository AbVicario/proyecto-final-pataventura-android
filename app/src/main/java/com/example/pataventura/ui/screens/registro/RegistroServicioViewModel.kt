package com.example.pataventura.ui.screens.registro

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RegistroServicioViewModel @Inject constructor(
) : ViewModel(){
    fun onPressRegistroServicio(navController:NavController){
        navController.navigate("home")
    }

}