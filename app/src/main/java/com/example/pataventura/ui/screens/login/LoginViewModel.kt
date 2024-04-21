package com.example.pataventura.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    //private val authenticateGuestUseCase: AuthenticateGuestUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _passEmpty = MutableLiveData<Boolean>()
    val passEmpty : LiveData<Boolean> = _passEmpty
    private val _emailEmpty = MutableLiveData<Boolean>()
    val emailEmpty : LiveData<Boolean> = _emailEmpty
    private val _emailNoValido = MutableLiveData<Boolean>()
    val emailNoValido : LiveData<Boolean> = _emailNoValido

    fun onEmailChange(email: String) {
        _email.postValue(email)
    }

    fun onPasswordChange(password: String) {
        _password.postValue(password)
    }

    fun onLoginPress(navController: NavController) {
        val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        val email = _email.value
        val password = _password.value
        _emailEmpty.postValue(false)
        _passEmpty.postValue(false)
        _emailNoValido.postValue(false)

        if(password.isNullOrBlank()){
            _passEmpty.postValue(true)
        }
        if(email.isNullOrBlank()){
            _emailEmpty.postValue(true)
        }else if(!regex.matches(email)){
            _emailNoValido.postValue(true)
        }
        if(_emailEmpty.value==false && _emailNoValido.value==false
            && _passEmpty.value==false){
            //Autenticar usuario
            navController.navigate(route = "home")
        }
    }

    fun onGooglePress(navController: NavController) {

        navController.navigate(route = "home")// para que no rompa

    }
    fun onRegistrarsePress(navController: NavController) {

        navController.navigate(route = "registerOne")// para que no rompa

    }
}
