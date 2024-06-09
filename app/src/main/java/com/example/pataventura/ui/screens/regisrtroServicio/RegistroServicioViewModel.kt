package com.example.pataventura.ui.screens.regisrtroServicio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.di.TiposServicio
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroServicioViewModel @Inject constructor(
    private val servicioRegisterUseCase: ServicioRegisterUseCase,
    private val getCuidadorUseCase: CuidadorGetUseCase
) : ViewModel() {

    var showDialog by mutableStateOf(false)
        private set

    private val _servicio = MutableLiveData<String>()
    val servicio: LiveData<String> = _servicio
    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion
    private val _precio = MutableLiveData<String>()
    val precio: LiveData<String> = _precio

    private val _isServicioSeleccionado = MutableLiveData<Boolean>()
    val isServicioSeleccionado: LiveData<Boolean> = _isServicioSeleccionado
    private val _isRango = MutableLiveData<Boolean>()
    val isRango: LiveData<Boolean> = _isRango
    private val _isDescripcion = MutableLiveData<Boolean>()
    val isDescripcion: LiveData<Boolean> = _isDescripcion
    private val _isPrecio = MutableLiveData<Boolean>()
    val isPrecio: LiveData<Boolean> = _isPrecio
    private val _isPrecioValido = MutableLiveData<Boolean>()
    val isPrecioValido: LiveData<Boolean> = _isPrecioValido

    private val _rango = MutableLiveData<String>("")
    val rango: LiveData<String> = _rango


    private val _listaRango = MutableLiveData<List<String>>(emptyList())
    val listaRango: LiveData<List<String>> = _listaRango

    private val _status = MutableLiveData<Int>()
    val status: LiveData<Int> = _status

    fun onChangeDescripcion(descripcion: String) {
        _descripcion.postValue(descripcion)
    }

    fun onChangeRango(rango: String) {
        _rango.postValue(rango)
    }

    fun onChangeServicio(servicio: String) {
        _servicio.postValue(servicio)
        _rango.postValue("")
        _listaRango.value = emptyList()
        for(tipo in TiposServicio.tiposServicio.value!!){
            if(tipo.tipo_oferta == servicio){
                _listaRango.value=tipo.kilometros
                break
            }
        }
    }

    fun onChangePrecio(precio: String) {
        _precio.postValue(precio)
    }


    fun onDialogConfirm(navController: NavController) {
        showDialog = false
        if (navController.previousBackStackEntry?.destination?.route != Destinations.Servicio.route) {
            navController.navigate(route = Destinations.Home.route)
        }

    }

    fun onOpenDialog() {
        showDialog = true
    }


    fun onPressRegistroServicio(navController: NavController) {
        viewModelScope.launch {
            val regex = Regex("^\\d*([,.]\\d+)?\$")
            val descripcion = _descripcion.value
            val precio = _precio.value
            val rango = _rango.value
            val servicio = _servicio.value
            _isDescripcion.postValue(false)
            _isPrecio.postValue(false)
            _isRango.postValue(false)
            _isServicioSeleccionado.postValue(false)
            _isPrecioValido.postValue(false)

            if (descripcion.isNullOrBlank()) {
                _isDescripcion.postValue(true)
            }
            if (rango.isNullOrBlank()) {
                _isRango.postValue(true)
            }
            if (servicio.isNullOrBlank()) {
                _isServicioSeleccionado.postValue(true)
            }
            if (precio.isNullOrBlank()) {
                _isPrecio.postValue(true)
            } else if (!regex.matches(precio)) {
                _isPrecioValido.postValue(true)
            }
            if (_isDescripcion.value == false && _isPrecio.value == false && _isPrecioValido.value == false
                && _isRango.value == false && _isServicioSeleccionado.value == false
            ) {

                val servicio = Servicio(
                    0, _servicio.value!!, _descripcion.value!!,
                    _precio.value!!.toFloat(), castRango(_rango.value!!,_servicio.value!!)
                )

                val response = servicioRegisterUseCase.registroServicio(servicio)

                if (response.ok) {
                    _servicio.postValue("")
                    _descripcion.postValue("")
                    _precio.postValue("")
                    _rango.postValue("")
                    if (navController.previousBackStackEntry?.destination?.route == Destinations.Servicio.route) {
                        navController.navigate(route = Destinations.Servicio.route)
                    } else {
                        val cuidador = getCuidadorUseCase.getCuidador()
                        navController.navigate(route = Destinations.PerfilTrabajador.route + "/${cuidador!!.idUsuario}")
                    }

                } else {
                    _status.postValue(response.status)
                    onOpenDialog()
                }

            }
        }
    }

    private fun castRango(rango: String, servicio: String): Int {
        var distancia: Int
        if(servicio == "Paseo"){
            distancia = rango.substring(0,rango.length-1).toInt()
        }else{
            val distanciaAux = rango.substring(0,rango.length-2).toFloat() * 1000
            distancia = distanciaAux.toInt()
        }
        return distancia
    }
}