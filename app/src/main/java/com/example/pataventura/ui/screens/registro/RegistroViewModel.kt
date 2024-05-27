package com.example.pataventura.ui.screens.registro

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Tutor
import com.example.pataventura.domain.useCase.AuthenticateUserUseCase
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorRegisterUseCase
import com.example.pataventura.domain.useCase.tutorUseCase.TutorRegisterUseCase
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val resgisterTutor: TutorRegisterUseCase,
    private val resgisterCuidador: CuidadorRegisterUseCase,
    private val authenticateUserUseCase: AuthenticateUserUseCase,
) : ViewModel() {
    var showDialog by mutableStateOf(false)
        private set

    private val _aliasEmpty = MutableLiveData<Boolean>()
    val aliasEmpty: LiveData<Boolean> = _aliasEmpty
    private val _emailEmpty = MutableLiveData<Boolean>()
    val emailEmpty: LiveData<Boolean> = _emailEmpty
    private val _emailNoValido = MutableLiveData<Boolean>()
    val emailNoValido: LiveData<Boolean> = _emailNoValido
    private val _passEmpty = MutableLiveData<Boolean>()
    val passEmpty: LiveData<Boolean> = _passEmpty
    private val _passConEmpty = MutableLiveData<Boolean>()
    val passConEmpty: LiveData<Boolean> = _passConEmpty

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
    private val _imagen = MutableLiveData<ImageBitmap>()
    val imagen: LiveData<ImageBitmap> = _imagen

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

    fun onImagenChange(imagen: ImageBitmap) {
        _imagen.postValue(imagen)
    }

    fun onDialogConfirm(navController: NavController) {
        showDialog = false
        navController.navigate(route = Destinations.LoginCliente.route)

    }

    fun onOpenDialog() {
        showDialog = true
    }

    fun onPressRegistroUno(navController: NavController) {
        viewModelScope.launch {
            val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
            val email = _email.value
            val password = _password.value
            val alias = _alias.value
            val passCon = _passCon.value
            var valid = true

            _emailEmpty.value = false
            _aliasEmpty.value = false
            _passEmpty.value = false
            _passConEmpty.value = false
            _emailNoValido.value = false

            if (alias.isNullOrBlank()) {
                _aliasEmpty.value = true
                valid = false
            }
            if (email.isNullOrBlank()) {
                _emailEmpty.value = true
                valid = false
            } else if (!regex.matches(email)) {
                _emailNoValido.value = true
                valid = false
            }
            if (password.isNullOrBlank()) {
                _passEmpty.value = true
                valid = false
            } else if (password != passCon) {
                _passConEmpty.value = true
                valid = false
            }

            if (valid) {
                navController.navigate(route = Destinations.RegisterTwo.route)
            }
        }
    }


    fun onRegistroDosButtonClicked(navController: NavController, tipo: String) {
        viewModelScope.launch {
            val nombre = _nombre.value
            val apellidos = _apellidos.value
            val direccion = _direccion.value
            val telefono = _telefono.value
            val imagen = _imagen.value
            var valid = true

            _nombreEmpty.value = false
            _apellidosEmpty.value = false
            _direccionEmpty.value = false
            _telefonoEmpty.value = false

            if (nombre.isNullOrBlank()) {
                _nombreEmpty.value = true
                valid = false
            }
            if (apellidos.isNullOrBlank()) {
                _apellidosEmpty.value = true
                valid = false
            }
            if (telefono.isNullOrBlank()) {
                _telefonoEmpty.value = true
                valid = false
            }
            if (direccion.isNullOrBlank()) {
                _direccionEmpty.value = true
                valid = false
            }

            if (valid) {
                val response: CustomResponse
                if (tipo == "tutor") {
                    val tutor = Tutor(
                        0, _email.value!!, _password.value!!,
                        _telefono.value!!, _nombre.value!!, _apellidos.value!!, ImageConverter.imageBitmapToByteArray(imagen!!),
                        _alias.value!!, _direccion.value!!
                    )
                    response = resgisterTutor.registroTutor(tutor)
                } else {
                    val cuidador = Cuidador(
                        0, _email.value!!, _password.value!!,
                        _telefono.value!!, _nombre.value!!, _apellidos.value!!, ImageConverter.imageBitmapToByteArray(imagen!!),
                        _alias.value!!, _direccion.value!!
                    )
                    response = resgisterCuidador.registroCuidador(cuidador)
                }

                if (response.ok) {
                    authenticateUserUseCase.login(
                        _email.value!!,
                        _password.value!!,
                        RoleHolder.rol.value.toString().lowercase()
                    )
                    if (tipo == "tutor") {
                        navController.navigate(route = Destinations.RegisterMascota.route)
                    } else {
                        navController.navigate(route = Destinations.RegistroServicio.route)
                    }
                } else {
                    onOpenDialog()
                }
            }
        }
    }

}