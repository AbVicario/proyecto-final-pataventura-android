package com.example.pataventura.ui.screens.loginCliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginClienteViewModel @Inject constructor(
) : ViewModel() {

    fun onPressLoginButton(navController: NavController) {
        navController.navigate("login")
    }

}