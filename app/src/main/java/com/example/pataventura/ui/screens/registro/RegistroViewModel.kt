package com.example.pataventura.ui.screens.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    //private val authenticateGuestUseCase: AuthenticateGuestUseCase
) : ViewModel() {
    private val _checkTutor = MutableLiveData<Boolean>()
    val checkTutor: LiveData<Boolean> = _checkTutor
    private val _checkCuidador = MutableLiveData<Boolean>()
    val checkCuidador: LiveData<Boolean> = _checkCuidador
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _alias = MutableLiveData<String>()
    val alias: LiveData<String> = _alias
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre
    private val _apellidos = MutableLiveData<String>()
    val apellidos: LiveData<String> = _apellidos
    private val _telefono = MutableLiveData<String>()
    val telefono: LiveData<String> = _telefono
    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion
    private val _repetirPassword = MutableLiveData<String>()
    val repetirPassword: LiveData<String> = _repetirPassword

    fun onEmailChange(email: String) {
        _email.postValue(email)
    }

    fun onPasswordChange(password: String) {
        _password.postValue(password)
    }

    fun onAliasChange(alias: String) {
        _alias.postValue(alias)
    }
    fun onNombreChange(nombre: String) {
        _nombre.postValue(nombre)
    }
    fun onTutorChange(checkTutor: Boolean) {
        _checkTutor.postValue(checkTutor)
    }
    fun onCuidadorChange(checkCuidador: Boolean) {
        _checkCuidador.postValue(checkCuidador)
    }
    fun onRepetirChange(repetirPassword: String) {
        if(repetirPassword.equals(password)){
            _repetirPassword.postValue(repetirPassword)
        }
    }

    fun onApellidosChange(apellidos: String) {
        _apellidos.postValue(apellidos)
    }

    fun onPhoneChange(telefono: String) {
        _telefono.postValue(telefono)
    }

    fun onHomeChange(direccion: String) {
        _direccion.postValue(direccion)
    }

    fun onPressRegistroUno(navController: NavController) {
        /*viewModelScope.launch {
            val email = _email.value
            val alias = _alias.value
            val password = _password.value
            val repetirPassword = _repetirPassword.value

            if (email.isNullOrBlank() || password.isNullOrBlank() ||
                alias.isNullOrBlank() || repetirPassword.isNullOrBlank()) {
                return@launch
            }else{
                navController.navigate(route = "registerTwo")
            }
        }*/
        navController.navigate(route = "registerTwo")
    }

    fun onPressRegistroDos(navController: NavController) {
        /*viewModelScope.launch {
            val nombre = _nombre.value
            val apellidos = _apellidos.value
            val telefono = _telefono.value

            if (nombre.isNullOrBlank() || apellidos.isNullOrBlank()
                || telefono.isNullOrBlank()) {
                return@launch
            }else{

                //navController.navigate(route = "registerMascota")
            }
        }*/
        navController.navigate(route = "registerMascota")
    }

}