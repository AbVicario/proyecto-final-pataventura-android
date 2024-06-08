package com.example.pataventura.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pataventura.domain.model.TiposMascota
import com.example.pataventura.domain.model.TiposServicio

object RoleHolder {
    private val _rol = MutableLiveData<String>()
    val rol: LiveData<String> = _rol

    fun setRol(value: String) {
        _rol.postValue(value)
    }

}

object IdCuidador {
    private val _idCuidador = MutableLiveData<Int>()
    val idCuidador: LiveData<Int> = _idCuidador

    fun setIdCuidador(value: Int) {
        _idCuidador.postValue(value)
    }

}

object NotificacionSize {
    private val _notificacionSize = MutableLiveData<Int>(0)
    val notificacionSize: LiveData<Int> = _notificacionSize
    fun setNotificacionSize(value: Int) {
        _notificacionSize.postValue(value)
    }
}

object TiposMascota {
    private val _tiposMascota = MutableLiveData<List<TiposMascota>>()
    val tiposMascota: LiveData<List<TiposMascota>> = _tiposMascota
    fun setTiposMascota(value: List<TiposMascota>) {
        _tiposMascota.postValue(value)
    }
}


object TiposServicio {
    private val _tiposServicio = MutableLiveData<List<TiposServicio>>()
    val tiposServicio: LiveData<List<TiposServicio>> = _tiposServicio

    fun setTiposServicio(value: List<TiposServicio>) {
        _tiposServicio.postValue(value)
    }

}


