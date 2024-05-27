package com.example.pataventura.ui.screens.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Ubicacion
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.mascotaUseCase.GetMascotasUseCase
import com.example.pataventura.domain.useCase.tutorUseCase.TutorGetUseCase
import com.example.pataventura.domain.useCase.ubicacionUseCase.UbicacionRegisterUseCase
import com.example.pataventura.domain.useCase.valoracionUseCase.GetValoracionUseCase
import com.example.pataventura.ui.screens.home.location.GetLocationUseCase
import com.example.pataventura.ui.screens.home.location.PermissionEvent
import com.example.pataventura.ui.screens.home.location.ViewState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getTutorUseCase: TutorGetUseCase,
    private val getMascotasUseCase: GetMascotasUseCase,
    private val registerUbicacionUseCase: UbicacionRegisterUseCase,
    private val getCuidadorUseCase : CuidadorGetUseCase,
    private val getValoracionesUseCase: GetValoracionUseCase
) : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _mascotas = MutableLiveData<List<Mascota>>()
    val mascotas: MutableLiveData<List<Mascota>> = _mascotas

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _servicio = MutableLiveData<String>()
    val servicio : LiveData<String> = _servicio

    private val _currentLocTutor = MutableLiveData<LatLng>()
    val currentLocTutor: LiveData<LatLng> = _currentLocTutor

    private val _listaCuidadores = MutableLiveData<List<Cuidador>>(emptyList())
    val listaCuidadores: LiveData<List<Cuidador>> = _listaCuidadores

    private val _listaCuidadoresPaseo = MutableLiveData<List<Cuidador>>(emptyList())
    val listaCuidadoresPaseo: LiveData<List<Cuidador>> = _listaCuidadoresPaseo

    private val _listaCuidadoresGuarderia = MutableLiveData<List<Cuidador>>(emptyList())
    val listaCuidadoresGuarderia: LiveData<List<Cuidador>> = _listaCuidadoresGuarderia

    private val _cameraState = MutableLiveData<CameraPositionState>()
    val cameraState: LiveData<CameraPositionState> = _cameraState

    private var isLocationSaved = false

    private val _valoraciones = MutableLiveData<List<Valoracion>>(emptyList())
    val valoraciones: MutableLiveData<List<Valoracion>> = _valoraciones

    private val _idMascota = MutableLiveData<Int>(0)
    val idMascota: MutableLiveData<Int> = _idMascota

    /*fun getValoraciones(idCuidador: Int) {
        viewModelScope.launch {
            _valoraciones.postValue(getValoracionesUseCase.getValoraciones(idCuidador))
            Log.d("Valoraciones", _valoraciones.value.toString())
        }
    }*/

    fun getValoraciones(idCuidador: Int): MutableLiveData<List<Valoracion>> {
        val valoracionesLiveData = MutableLiveData<List<Valoracion>>()
        viewModelScope.launch {
            val valoraciones = getValoracionesUseCase.getValoraciones(idCuidador)
            valoracionesLiveData.postValue(valoraciones)
            Log.d("Valoraciones", valoraciones.toString())
        }
        return valoracionesLiveData
    }


    fun onRolChange(rol: String){
        _servicio.postValue(rol)
    }

    fun onIdMascotaChange(id: Int){
        _idMascota.postValue(id)
    }

    fun setNombre(){
        viewModelScope.launch {
            try {
                val tutor = getTutorUseCase.getTutor()
                if(tutor != null) {
                    _nombre.postValue(tutor.nombre)
                }else{
                    _nombre.postValue("No hay tutor")
                }
            }catch (e: Exception){
                throw e
            }

        }

    }
    suspend fun getMascotas(){
        viewModelScope.launch {
            val result = getMascotasUseCase.getMascotas()
            _mascotas.postValue(result)
        }
    }

    fun handle(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                viewModelScope.launch {
                    getLocationUseCase.invoke().collect { location ->
                        _viewState.value = ViewState.Success(location)
                        if (!isLocationSaved) {
                            val coordendas =
                                "(" + location!!.latitude + "," + location.longitude + ")"
                            val ubicacion = Ubicacion(
                                0,
                                coordendas
                            )
                            val response = registerUbicacionUseCase.registroUbicacionTutor(ubicacion)
                            getClasificaCuidadores(getCuidadorUseCase.getCuidadoresByDistance())
                            if (response.status == 200) {
                                isLocationSaved = true
                            }
                        }
                    }
                }
            }

            PermissionEvent.Revoked -> {
                _viewState.value = ViewState.RevokedPermissions
            }
        }
    }

    private fun getClasificaCuidadores(allCuidadores: List<Cuidador>) {
        _listaCuidadores.value = allCuidadores

        val listaPaseo = mutableListOf<Cuidador>()
        val listaGuarderia = mutableListOf<Cuidador>()

        for (cuidador in allCuidadores) {
            if (cuidador.servicio?.tipo == "paseo") {
                listaPaseo.add(cuidador)
            } else {
                listaGuarderia.add(cuidador)
            }
        }

        _listaCuidadoresPaseo.value = listaPaseo
        _listaCuidadoresGuarderia.value = listaGuarderia
    }


    fun setCurrentLoc(loc: LatLng) {
        _currentLocTutor.postValue(loc)
    }

    fun setCameraState(cameraState: CameraPositionState) {
        _cameraState.postValue(cameraState)
    }
}