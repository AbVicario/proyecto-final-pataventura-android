package com.example.pataventura.ui.screens.servicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.data.model.ServicioModel
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.Tutor
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioGetUseCase
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioRegisterUseCase
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicioViewModel @Inject constructor(
    private val getServicioUseCase: ServicioGetUseCase,
    private val servicioUpdateUseCase: ServicioUpdateUseCase
) : ViewModel() {


    private val _listaServicios = MutableLiveData<List<Servicio>>()
    val listaServicios: LiveData<List<Servicio>> = _listaServicios

    private val _isPrecio = MutableLiveData<Boolean>()
    val isPrecio: LiveData<Boolean> = _isPrecio

    private val _isDescripcion = MutableLiveData<Boolean>()
    val isDescripcion: LiveData<Boolean> = _isDescripcion

    private val _isRadio = MutableLiveData<Boolean>()
    val isRadio: LiveData<Boolean> = _isRadio

    private val _precio = MutableLiveData<String>()
    val precio: LiveData<String> = _precio

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _radio = MutableLiveData<String>()
    val radio: LiveData<String> = _radio

    private val _editMode = MutableLiveData<Boolean>()
    val editMode: LiveData<Boolean> = _editMode

    fun onValueChangePrecio(precio: String) {
        _precio.postValue(precio)

    }

    fun onValueChangeDescripcion(descripcion: String) {
        _descripcion.postValue(descripcion)
    }

    fun onValueChangeRadio(radio: String) {
        _radio.postValue(radio)

    }
    fun pintarServicios(){
        viewModelScope.launch {
            val servicios = getServicioUseCase.getServicios()
            if(servicios.isNotEmpty()){
                _listaServicios.postValue(servicios)
            }
        }
    }

    fun onSave(navController: NavController, idServicio: Int, tipo: String) {
        viewModelScope.launch {
            if (validarCampos()) {
                val response = servicioUpdateUseCase.updateServicio(
                    Servicio(
                        idOferta = idServicio,
                        tipo = tipo,
                        precio = precio.value!!.toFloat(),
                        descripcion = _descripcion.value.toString(),
                        radio = formatRadio(_radio.value.toString(), tipo),

                    )
                )
                if(response.ok){
                    navController.navigate("home")
                }
            }
        }
    }



    private fun formatRadio(radio: String, tipo: String): Int {
        val distancia: Int
        if (tipo == "Paseo") {
            distancia = radio.substring(0, radio.length - 1).toInt()
        } else {
            val distanciaAux = radio.substring(0, radio.length - 2).toFloat() * 1000
            distancia = distanciaAux.toInt()
        }
        return distancia
    }

    private fun validarCampos(): Boolean {
        val isValid = true
        if (_precio.value.isNullOrEmpty()) {
            !isValid
        }
        if (_descripcion.value.isNullOrEmpty()) {
            !isValid
        }
        if (_radio.value.isNullOrEmpty()) {
            !isValid
        }
        return isValid
    }


}
