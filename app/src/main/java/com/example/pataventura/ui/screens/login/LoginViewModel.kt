package com.example.pataventura.ui.screens.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    //private val authenticateGuestUseCase: AuthenticateGuestUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _tieneError: ObservableBoolean =
        ObservableBoolean(false)
    val tieneError: ObservableBoolean
        get() = _tieneError

    fun onEmailChange(email: String) {
        _email.postValue(email)
    }

    fun onPasswordChange(password: String) {
        _password.postValue(password)
    }

    fun onLoginPress(navController: NavController) {
       /* viewModelScope.launch {
            val email = _email.value
            val password = _password.value

            if (email.isNullOrBlank() || password.isNullOrBlank()) {
                _tieneError.set(true)
                return@launch
            } else {
                navController.navigate(route = "home")// para que no rompa
            }


            //val isSuccessful = authenticateGuestUseCase.login(email, password)

            //if (isSuccessful) {
            //    navController.navigate(route = "home")
            //}
            //}
        }*/
        navController.navigate(route = "home")// para que no rompa
    }

    fun onGooglePress(navController: NavController) {

        navController.navigate(route = "home")// para que no rompa

    }
    fun onRegistrarsePress(navController: NavController) {

        navController.navigate(route = "registerOne")// para que no rompa

    }
}
