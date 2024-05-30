package com.example.pataventura.ui.screens.contratacion

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Demanda
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.useCase.demandaUseCase.DemandaRegisterUseCase
import com.example.pataventura.domain.useCase.valoracionUseCase.GetValoracionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ContratacionViewModel @Inject constructor(
    private val getValoracionesUseCase: GetValoracionUseCase,
    private val registerDemandaUseCase: DemandaRegisterUseCase
) : ViewModel(

) {
    private val _precioTotal = MutableLiveData("0")
    val precioTotal: LiveData<String> = _precioTotal

    private val _cuidador = MutableLiveData(Cuidador())
    val cuidador: MutableLiveData<Cuidador> = _cuidador

    private val _servicio = MutableLiveData(Servicio())
    val servicio: LiveData<Servicio> = _servicio


    private val _idCuidador = MutableLiveData<Int>()
    val idCuidador: LiveData<Int> = _idCuidador

    private val _idServicio = MutableLiveData<Int>()
    val idServicio: LiveData<Int> = _idServicio

    private val _idMascota = MutableLiveData<Int>()
    val idMascota: LiveData<Int> = _idMascota

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

    fun setServicioId(id_servicio: Int) {
        _idServicio.postValue(id_servicio)
    }

    fun setMascotaId(idMascota: Int) {
        _idMascota.postValue(idMascota)
    }

    fun setServicio(servicio: Servicio) {
        _servicio.postValue(servicio)
    }

    fun setCuidador(cuidadores: List<Cuidador>) {
        try {
            for (cuidador in cuidadores) {
                if (cuidador.idUsuario == idCuidador.value && cuidador.servicio!!.idOferta == idServicio.value) {
                    _cuidador.postValue(cuidador)
                    _servicio.postValue(cuidador.servicio)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getValoraciones(idCuidador: Int) {
        _valoraciones.postValue(getValoracionesUseCase.getValoraciones(idCuidador))
    }

    fun onNotasChange(notas: String) {
        _notas.postValue(notas)
    }

    fun onFechaInicioChange(fechaInicio: LocalDateTime) {
        _fechaInicio.postValue(fechaInicio)
    }

    fun onFechaFinChange(fechaFin: LocalDateTime) {
        _fechaFin.postValue(fechaFin)
    }

    fun calcularPrecio(precio: Float, numDias: Int) {
        _precioTotal.postValue((precio * numDias).toString())
    }

    fun contratar(navController: NavController, context: Context) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val fechaInicioFormat = fechaInicio.value!!.format(formatter)
        val fechaFinFormat = fechaFin.value!!.format(formatter)

        viewModelScope.launch {
            val response = registerDemandaUseCase.registerDemanda(
                Demanda(
                    id_demanda = 0,
                    fechaInicio = fechaInicioFormat,
                    fechaFin = fechaFinFormat,
                    precio = precioTotal.value!!.toDouble(),
                    descripcion = notas.value ?:"",
                    estado = "demanda tutor",
                    id_mascota = idMascota.value!!,
                    id_servicio = idServicio.value!!,
                )
            )
            if (response.ok) {
                Toast.makeText(context, "Demanda registrada", Toast.LENGTH_SHORT).show()
                navController.navigate(Destinations.Home.route)
            }
        }

    }
}