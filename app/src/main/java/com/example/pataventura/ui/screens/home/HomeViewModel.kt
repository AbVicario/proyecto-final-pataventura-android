package com.example.pataventura.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Ubicacion
import com.example.pataventura.domain.useCase.mascotaUseCase.GetMascotasUseCase
import com.example.pataventura.domain.useCase.tutorUseCase.TutorGetUseCase
import com.example.pataventura.domain.useCase.ubicacionUseCase.UbicacionRegisterUseCase
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
    private val registerUbicacionUseCase: UbicacionRegisterUseCase
) : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _mascotas = MutableLiveData<List<Mascota>>()
    val mascotas: MutableLiveData<List<Mascota>> = _mascotas

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _servicio = MutableLiveData<String>()
    val servicio : LiveData<String> = _servicio

    private val _currentLoc = MutableLiveData<LatLng>()
    val currentLoc: LiveData<LatLng> = _currentLoc

    private val _cameraState = MutableLiveData<CameraPositionState>()
    val cameraState: LiveData<CameraPositionState> = _cameraState


    fun onRolChange(rol: String){
        _servicio.postValue(rol)
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
                        val coordendas = "("+location!!.latitude+","+location.longitude+")"
                        val ubicacion = Ubicacion(
                            0,
                            coordendas)
                        registerUbicacionUseCase.registroUbicacionTutor(ubicacion)
                        _viewState.value = ViewState.Success(location)
                    }
                }
            }

            PermissionEvent.Revoked -> {
                _viewState.value = ViewState.RevokedPermissions
            }
        }
    }

    fun setCurrentLoc(loc: LatLng) {
        _currentLoc.postValue(loc)
    }

    fun setCameraState(cameraState: CameraPositionState) {
        _cameraState.postValue(cameraState)
    }
}