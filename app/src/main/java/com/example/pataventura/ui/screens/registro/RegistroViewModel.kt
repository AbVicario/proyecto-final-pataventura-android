package com.example.pataventura.ui.screens.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    //private val authenticateGuestUseCase: AuthenticateGuestUseCase
) : ViewModel() {
    private val _aliasEmpty = MutableLiveData<Boolean>()
    val aliasEmpty: LiveData<Boolean> = _aliasEmpty
    private val _emailEmpty = MutableLiveData<Boolean>()
    val emailEmpty: LiveData<Boolean> = _emailEmpty
    private val _emailNoValido = MutableLiveData<Boolean>()
    val emailNoValido: LiveData<Boolean> = _emailNoValido
    private val _passEmpty = MutableLiveData<Boolean>()
    val passEmpty: LiveData<Boolean> = _passEmpty
    private val _passConEmpty = MutableLiveData<Boolean>()
    val passConEmpty : LiveData<Boolean> = _passConEmpty

    private val _nombreEmpty = MutableLiveData<Boolean>()
    val nombreEmpty: LiveData<Boolean> = _nombreEmpty
    private val _apellidosEmpty = MutableLiveData<Boolean>()
    val apellidosEmpty: LiveData<Boolean> = _apellidosEmpty
    private val _telefonoEmpty = MutableLiveData<Boolean>()
    val telefonoEmpty: LiveData<Boolean> = _telefonoEmpty
    private val _direccionEmpty = MutableLiveData<Boolean>()
    val direccionEmpty: LiveData<Boolean> = _direccionEmpty

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _passCon = MutableLiveData<String>()
    val passCon: LiveData<String> = _passCon
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
    fun onPasswordConChange(passCon: String) {
        _passCon.postValue(passCon)
    }

    fun onAliasChange(alias: String) {
        _alias.postValue(alias)
    }
    fun onNombreChange(nombre: String) {
        _nombre.postValue(nombre)
    }

    fun onApellidosChange(apellidos: String) {
        _apellidos.postValue(apellidos)
    }

    fun onPhoneChange(telefono: String) {
        _telefono.postValue(telefono)
    }

    fun onDireccionChange(direccion: String) {
        _direccion.postValue(direccion)
    }

    fun onPressRegistroUno(navController: NavController) {
        val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        val email = _email.value
        val password = _password.value
        val alias = _alias.value
        val passCon = _passCon.value
        _emailEmpty.postValue(false)
        _aliasEmpty.postValue(false)
        _passEmpty.postValue(false)
        _passConEmpty.postValue(false)
        _emailNoValido.postValue(false)

        if(alias.isNullOrBlank()){
            _aliasEmpty.postValue(true)
        }
        if(email.isNullOrBlank()){
            _emailEmpty.postValue(true)
        }else if(!regex.matches(email)){
            _emailNoValido.postValue(true)
        }
        if(password.isNullOrBlank()){
            _passEmpty.postValue(true)
        }else if(password != passCon){
            _passConEmpty.postValue(true)
        }

        if(_aliasEmpty.value==false && _emailEmpty.value==false && _emailNoValido.value==false
            && _passEmpty.value==false && _passConEmpty.value == false ){
            navController.navigate(route = Destinations.RegisterTwo.route)
        }
    }

    fun onPressRegistroDos(navController: NavController) {
        val nombre = _nombre.value
        val apellidos = _apellidos.value
        val direccion = _direccion.value
        val telefono = _telefono.value
        _nombreEmpty.postValue(false)
        _apellidosEmpty.postValue(false)
        _direccionEmpty.postValue(false)
        _telefonoEmpty.postValue(false)

        if(nombre.isNullOrBlank()){
            _nombreEmpty.postValue(true)
        }
        if(apellidos.isNullOrBlank()){
            _apellidosEmpty.postValue(true)
        }

        if(telefono.isNullOrBlank()){
            _telefonoEmpty.postValue(true)
        }
        if(direccion.isNullOrBlank()) {
            _direccionEmpty.postValue(true)
        }

        if(_nombreEmpty.value==false && _apellidosEmpty.value==false && _telefonoEmpty.value==false
            && _direccionEmpty.value == false ){
            navController.navigate(route = Destinations.RegisterMascota.route)
        }
    }

}