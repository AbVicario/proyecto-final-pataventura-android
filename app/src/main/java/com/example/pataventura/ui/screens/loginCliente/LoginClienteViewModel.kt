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
    //private val authenticateGuestUseCase: AuthenticateGuestUseCase
) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun onEmailChange(email: String) {
        _email.postValue(email)
    }

    fun onPasswordChange(password: String) {
        _password.postValue(password)
    }

    fun onLoginPress(navController: NavController) {
        viewModelScope.launch {
            val email = _email.value
            val password = _password.value

            if (email.isNullOrBlank() || password.isNullOrBlank()) {
                return@launch
            }

            /*val isSuccessful = authenticateGuestUseCase.login(email, password)

            if (isSuccessful) {
                navController.navigate(route = "home")
            }*/
        }
    }
}