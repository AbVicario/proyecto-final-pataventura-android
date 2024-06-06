package com.example.pataventura.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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
    private val _tiposMascota = MutableLiveData<List<String>>()
    val tiposMascota: LiveData<List<String>> = _tiposMascota

    fun setTiposMascota(value: List<String>) {
        _tiposMascota.postValue(value)
    }
}

object RazasMascota {
    private val _razasMascota = MutableLiveData<List<String>>()
    val razasMascota: LiveData<List<String>> = _razasMascota

    fun setTiposMascota(value: List<String>) {
        _razasMascota.postValue(value)
    }
}

object TiposServicio {
    private val _tiposServicio = MutableLiveData<List<String>>()
    val tiposServicio: LiveData<List<String>> = _tiposServicio

    fun setTiposServicio(value: List<String>) {
        _tiposServicio.postValue(value)
    }
}

object DistanciasServicio {
    private val _distanciasServicio = MutableLiveData<List<String>>()
    val distanciasServicio: LiveData<List<String>> = _distanciasServicio

    fun setDistanciasServicio(value: List<String>) {
        _distanciasServicio.postValue(value)
    }
}
