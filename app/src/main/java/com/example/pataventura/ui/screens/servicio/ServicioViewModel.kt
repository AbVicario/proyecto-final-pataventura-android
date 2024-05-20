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
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioDeleteUseCase
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioGetUseCase
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioRegisterUseCase
import com.example.pataventura.domain.useCase.servicioUseCase.ServicioUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicioViewModel @Inject constructor(
    private val getServicioUseCase: ServicioGetUseCase,
    private val servicioUpdateUseCase: ServicioUpdateUseCase,
    private val servicioDeleteUseCase: ServicioDeleteUseCase
) : ViewModel() {

    private val _listaServicios = MutableLiveData<List<Servicio>>()
    val listaServicios: LiveData<List<Servicio>> = _listaServicios

    private val _servicioStates = MutableLiveData<Map<Int, ServicioState>>()
    val servicioStates: LiveData<Map<Int, ServicioState>> = _servicioStates


    fun pintarServicios() {
        viewModelScope.launch {
            val servicios = getServicioUseCase.getServicios()
            if (servicios.isNotEmpty()) {
                _listaServicios.postValue(servicios)
                val initialStates = servicios.associateBy({ it.idOferta }, { ServicioState(it) })
                _servicioStates.postValue(initialStates)
            }
        }
    }

    fun onValueChangePrecio(id: Int, precio: String) {
        updateServicioState(id) { it.copy(precio = precio) }
    }

    fun onValueChangeDescripcion(id: Int, descripcion: String) {
        updateServicioState(id) { it.copy(descripcion = descripcion) }
    }

    fun onValueChangeRadio(id: Int, radio: String) {
        updateServicioState(id) { it.copy(radio = radio) }
    }

    fun onValueChangeEditMode(id: Int, edit: Boolean) {
        updateServicioState(id) { it.copy(editMode = edit) }
    }

    private fun updateServicioState(id: Int, update: (ServicioState) -> ServicioState) {
        _servicioStates.value = _servicioStates.value?.toMutableMap()?.apply {
            this[id] = update(this[id] ?: ServicioState())
        }
    }

    fun onSave(navController: NavController, servicio: Servicio) {
        viewModelScope.launch {
            val estado = _servicioStates.value?.get(servicio.idOferta) ?: return@launch
            if (validarCampos(estado)) {
                val response = servicioUpdateUseCase.updateServicio(
                    Servicio(
                        idOferta = servicio.idOferta,
                        tipo = servicio.tipo,
                        precio = estado.precio.toFloat(),
                        descripcion = estado.descripcion,
                        radio = formatRadio(estado.radio, servicio.tipo),
                    )
                )
                if (response.ok) {
                    onValueChangeEditMode(servicio.idOferta, false)
                    navController.navigate("home")
                }
            }
        }
    }

    private fun validarCampos(estado: ServicioState): Boolean {
        return estado.precio.isNotEmpty() && estado.descripcion.isNotEmpty() && estado.radio.isNotEmpty()
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

    fun onDelete(servicio: Servicio) {
        viewModelScope.launch {
            val response = servicioDeleteUseCase.deleteServicio(servicio)
            if (response.ok) {
                _listaServicios.postValue(getServicioUseCase.getServicios())
            }
        }
    }

    data class ServicioState(
        val servicio: Servicio = Servicio(),
        val precio: String = servicio.precio.toString(),
        val descripcion: String = servicio.descripcion,
        val radio: String = servicio.radio.toString(),
        val editMode: Boolean = false
    )

}
