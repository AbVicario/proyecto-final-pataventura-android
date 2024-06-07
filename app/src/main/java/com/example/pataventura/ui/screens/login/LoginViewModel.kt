package com.example.pataventura.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.di.IdCuidador
import com.example.pataventura.di.NotificacionSize
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.Notificacion
import com.example.pataventura.domain.model.TiposMascota
import com.example.pataventura.domain.model.TiposServicio
import com.example.pataventura.domain.useCase.AuthenticateUserUseCase
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.notificacionUseCase.GetNotificacionesUseCase
import com.example.pataventura.domain.useCase.tiposUseCase.GetTipoMascotaUseCase
import com.example.pataventura.domain.useCase.tiposUseCase.GetTipoOfertaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUserUseCase,
    private val getUseCase: CuidadorGetUseCase,
    private val getNotificacionesUseCase: GetNotificacionesUseCase,
    private val getTipoMascotaUseCase: GetTipoMascotaUseCase,
    private val getTipoOfertaUseCase: GetTipoOfertaUseCase
) : ViewModel() {

    var showDialog by mutableStateOf(false)
        private set

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _notificaciones = MutableLiveData<List<Notificacion>>(emptyList())
    val notificaciones: LiveData<List<Notificacion>> = _notificaciones
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _passEmpty = MutableLiveData<Boolean>()
    val passEmpty: LiveData<Boolean> = _passEmpty
    private val _emailEmpty = MutableLiveData<Boolean>()
    val emailEmpty: LiveData<Boolean> = _emailEmpty
    private val _emailNoValido = MutableLiveData<Boolean>()
    val emailNoValido: LiveData<Boolean> = _emailNoValido

    fun onOpenDialog() {
        showDialog = true
    }

    fun onDialogConfirm() {
        showDialog = false
    }

    fun onEmailChange(email: String) {
        _email.postValue(email)
    }

    fun onPasswordChange(password: String) {
        _password.postValue(password)
    }

    fun onLoginButtonClicked(navController: NavController) {
        if (validateInputs()) {
            viewModelScope.launch {
                onLoginPress(navController)
            }
        }
    }

    fun onNotificacionesChange(notificaciones: List<Notificacion>) {
        _notificaciones.postValue(notificaciones)
    }

    private fun validateInputs(): Boolean {
        val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        val email = _email.value
        val password = _password.value
        var isValid = true

        _emailEmpty.value = false
        _passEmpty.value = false
        _emailNoValido.value = false

        if (password.isNullOrBlank()) {
            _passEmpty.value = true
            isValid = false
        }
        if (email.isNullOrBlank()) {
            _emailEmpty.value = true
            isValid = false
        } else if (!regex.matches(email)) {
            _emailNoValido.value = true
            isValid = false
        }

        return isValid
    }

    suspend fun onLoginPress(navController: NavController) {
        val email = _email.value!!
        val password = _password.value!!
        var nombre = ""

        val result =
            authenticateUseCase.login(email, password, RoleHolder.rol.value.toString().lowercase())
        if (result) {
            val notificaciones: List<Notificacion> =
                getNotificacionesUseCase.getNotificaciones(RoleHolder.rol.value.toString().lowercase())
            contadorNuevasNotificaciones(notificaciones)
            _notificaciones.postValue(notificaciones)
            if (RoleHolder.rol.value.toString().lowercase() == "tutor") {
                setTipoMascota(getTipoMascotaUseCase.getTiposMascota())
                navController.navigate(route = "Home")
            } else {
                val cuidador = getUseCase.getCuidador()
                IdCuidador.setIdCuidador(cuidador!!.idUsuario)
                setTipoOferta(getTipoOfertaUseCase.getTiposOferta())
                navController.navigate(Destinations.PerfilTrabajador.route + "/${cuidador.idUsuario}")
            }
        } else {
            onOpenDialog()
        }
    }

    private fun setTipoOferta(tiposOferta: List<TiposServicio>) {
        var tipos: MutableList<String> = mutableListOf()
        for(tipo in tiposOferta){
            tipos.add(tipo.tipo_oferta)
        }
        com.example.pataventura.di.TiposServicio.setTiposServicio(tipos)
    }

    private fun setTipoMascota(tiposMascota: List<TiposMascota>) {
        var tipos: MutableList<String> = mutableListOf()
        for(tipo in tiposMascota){
            tipos.add(tipo.tipo_mascota)
        }
        com.example.pataventura.di.TiposMascota.setTiposMascota(tipos)
    }

    fun onGooglePress(navController: NavController) {

        navController.navigate(route = "home")

    }

    fun onRegistrarsePress(navController: NavController) {
        navController.navigate(route = "registerOne")
    }

    fun contadorNuevasNotificaciones(notificaciones: List<Notificacion>) {
        var cont = 0
        for (notificacion in notificaciones) {
            if (notificacion.estado.lowercase() == "nueva") {
                cont++
            }
        }
        NotificacionSize.setNotificacionSize(cont)
    }
}
