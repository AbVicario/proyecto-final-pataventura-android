package com.example.pataventura.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {
    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _currentLoc = MutableLiveData<LatLng>()
    val currentLoc: LiveData<LatLng> = _currentLoc

    private val _cameraState = MutableLiveData<CameraPositionState>()
    val cameraState: LiveData<CameraPositionState> = _cameraState

    /* This function is responsible for updating the ViewState based
       on the event coming from the view */
    fun handle(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                viewModelScope.launch {
                    getLocationUseCase.invoke().collect { location ->
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