package com.example.pataventura.ui.screens.perfil_trabajador

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Ubicacion
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.ubicacionUseCase.UbicacionRegisterUseCase
import com.example.pataventura.domain.useCase.valoracionUseCase.GetValoracionUseCase
import com.example.pataventura.ui.screens.home.location.GetLocationUseCase
import com.example.pataventura.ui.screens.home.location.PermissionEvent
import com.example.pataventura.ui.screens.home.location.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class
PerfilTrabajadorViewModel @Inject constructor(
    private val getCuidadorUseCase: CuidadorGetUseCase,
    private val getValoracionesUseCase: GetValoracionUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val registerUbicacionUseCase: UbicacionRegisterUseCase
) : ViewModel() {

    private val _idCuidador = MutableLiveData<Int>()
    val idCuidador: LiveData<Int> = _idCuidador

    private val _cuidador = MutableLiveData(Cuidador())
    val cuidador: MutableLiveData<Cuidador> = _cuidador

    private val _valoraciones = MutableLiveData<List<Valoracion>>(emptyList())
    val valoraciones: MutableLiveData<List<Valoracion>> = _valoraciones

    private var isLocationSaved = false

    suspend fun getValoraciones(idCuidador: Int) {
        _valoraciones.postValue(getValoracionesUseCase.getValoraciones(idCuidador))
        Log.d("Valoraciones", _valoraciones.value.toString())
    }

    suspend fun setCuidador(cuidadores: List<Cuidador>) {
        try {
            viewModelScope.launch {
                val rol = RoleHolder.rol.value.toString().lowercase()
                if (rol == "cuidador") {
                    _cuidador.postValue(getCuidadorUseCase.getCuidador())
                    Log.d("Cuidador", _cuidador.value.toString())
                } else {
                    for (cuidador in cuidadores) {
                        if (cuidador.idUsuario == idCuidador.value) {
                            _cuidador.postValue(cuidador)
                        }
                    }
                }
                getValoraciones(_cuidador.value!!.idUsuario)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun setTrabajadorId(idCuidador: Int) {
        _idCuidador.postValue(idCuidador)
    }

    /*@RequiresApi(Build.VERSION_CODES.S)
    fun handle() {
        viewModelScope.launch {
            getLocationUseCase.invoke().collect { location ->
                if (!isLocationSaved) {
                    val coordendas =
                        "(" + location!!.latitude + "," + location.longitude + ")"
                    val ubicacion = Ubicacion(
                        0,
                        coordendas
                    )
                    val response = registerUbicacionUseCase.registroUbicacionCuidador(ubicacion)
                    if (response.status == 200) {
                        isLocationSaved = true
                    }
                }
            }
        }
    }*/
}
