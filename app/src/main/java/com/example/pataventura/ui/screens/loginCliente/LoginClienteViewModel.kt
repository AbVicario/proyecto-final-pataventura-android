package com.example.pataventura.ui.screens.loginCliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginClienteViewModel @Inject constructor(
) : ViewModel() {

    fun onPressLoginTutorButton(navController: NavController) {
        RoleHolder.setRol("tutor")
        navController.navigate("login")
    }

    fun onPressLoginCuidadorButton(navController: NavController) {
        RoleHolder.setRol("cuidador")
        navController.navigate("login")
    }

}