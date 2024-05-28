package com.example.pataventura.ui.screens.contratacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.useCase.valoracionUseCase.GetValoracionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ContratacionViewModel @Inject constructor(
    private val getValoracionesUseCase: GetValoracionUseCase

) : ViewModel(

) {
    private val _precioTotal = MutableLiveData<String>()
    val precioTotal: LiveData<String> = _precioTotal

    private val _cuidador = MutableLiveData(Cuidador())
    val cuidador: MutableLiveData<Cuidador> = _cuidador

    private val _servicio = MutableLiveData<String>()
    val servicio: LiveData<String> = _servicio


    private val _idCuidador = MutableLiveData<Int>()
    val idCuidador: LiveData<Int> = _idCuidador

    private val _fechaInicio = MutableLiveData<LocalDateTime>()
    val fechaInicio: LiveData<LocalDateTime> = _fechaInicio

    private val _fechaFin = MutableLiveData<LocalDateTime>()
    val fechaFin: LiveData<LocalDateTime> = _fechaFin

    private val _valoraciones = MutableLiveData<List<Valoracion>>()
    val valoraciones: LiveData<List<Valoracion>> = _valoraciones

    private val _notas = MutableLiveData<String>()
    val notas: LiveData<String> = _notas

    fun setTrabajadorId(id: Int) {
        _idCuidador.value = id
        viewModelScope.launch {
            getValoraciones(id)
        }
    }

    fun setCuidador(cuidadores: List<Cuidador>) {
        try {
            for (cuidador in cuidadores) {
                if (cuidador.idUsuario == idCuidador.value) {
                    _cuidador.postValue(cuidador)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun setServicio(servicio: String) {
        _servicio.postValue(servicio)
    }

    suspend fun getValoraciones(idCuidador: Int) {
        _valoraciones.postValue(getValoracionesUseCase.getValoraciones(idCuidador))
    }

    fun onNotasChange(notas: String) {
        _notas.postValue(notas)
    }

    /*fun onFechaInicioChange(fechaInicio: LocalDateTime?) {
        _fechaInicio.postValue(fechaInicio)
    }

    fun onFechaFinChange(fechaFin: LocalDateTime?) {
        _fechaFin.postValue(fechaFin)
    }*/
    fun calcularPrecio(precio: Float, numDias: Int) {
        _precioTotal.postValue((precio * numDias).toString())
    }
}