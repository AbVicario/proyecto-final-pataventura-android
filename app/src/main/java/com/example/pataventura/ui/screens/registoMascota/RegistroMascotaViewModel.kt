package com.example.pataventura.ui.screens.registoMascota

import android.content.Context
import android.database.Observable
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroMascotaViewModel @Inject constructor(
    private val mascotaRegisterUseCase: MascotaRegisterUseCase
) : ViewModel() {
    var showDialog by mutableStateOf(false)
        private set

    private val _nombreEmpty = MutableLiveData<Boolean>()
    val nombreEmpty: LiveData<Boolean> = _nombreEmpty
    private val _tipoEmpty = MutableLiveData<Boolean>()
    val tipoEmpty: LiveData<Boolean> = _tipoEmpty
    private val _numChipEmpty = MutableLiveData<Boolean>()
    val numChipEmpty: LiveData<Boolean> = _numChipEmpty
    private val _colorEmpty = MutableLiveData<Boolean>()
    val colorEmpty: LiveData<Boolean> = _colorEmpty


    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre
    private val _color = MutableLiveData<String>()
    val color: LiveData<String> = _color
    private val _tamanyo = MutableLiveData<String>("")
    val tamanyo: LiveData<String> = _tamanyo
    private val _sexo = MutableLiveData<String>("")
    val sexo: LiveData<String> = _sexo
    private val _tipo = MutableLiveData<String>()
    val tipo: LiveData<String> = _tipo
    private val _raza = MutableLiveData<String>("")
    val raza: LiveData<String> = _raza
    private val _edad = MutableLiveData<String>("")
    val edad: LiveData<String> = _edad
    private val _peso = MutableLiveData<Double>(0.0)
    val peso: LiveData<Double> = _peso
    private val _observacion = MutableLiveData<String>("")
    val observacion: LiveData<String> = _observacion
    private val _numChip = MutableLiveData<String>()
    val numChip: LiveData<String> = _numChip
    private val _imagen = MutableLiveData<ImageBitmap>()
    val imagen: LiveData<ImageBitmap> = _imagen


    private val _listaRaza: ObservableList<String> = ObservableArrayList<String>().apply {
        add("Campo Vacio")
    }
    val listaRaza: ObservableList<String> = _listaRaza

    private val _colorAsig = MutableLiveData<String>()
    val colorAsig: LiveData<String> = _colorAsig

    private val _isTipoElegido = MutableLiveData<Boolean>()
    val isTipoElegido: LiveData<Boolean> = _isTipoElegido


    var itemsRazaGato = listOf<String>(
        "Siames",
        "Europeo",
        " "
    )
    var itemsRazaPerro = listOf<String>(
        "Bichón",
        "Pekines",
        " "
    )


    fun pintarItemsRaza(tipo: String) {
        val itemsToAdd: MutableList<String> = when (tipo) {
            "Perro" -> itemsRazaPerro.toMutableList()
            "Gato" -> itemsRazaGato.toMutableList()
            else -> itemsRazaGato.toMutableList()
        }
        _listaRaza.clear()
        _listaRaza.addAll(itemsToAdd)
    }

    fun onNombreChange(nombre: String) {
        _nombre.postValue(nombre)
    }

    fun onTipoChange(tipo: String) {
        _tipo.postValue(tipo)
        pintarItemsRaza(tipo)
    }

    fun onRazaChange(raza: String) {
        _raza.postValue(raza)
    }

    fun onEdadChange(edad: String) {
        _edad.postValue(edad)
    }

    fun onPesoChange(peso: Double) {
        _peso.postValue(peso)
    }

    fun onObservacionChange(observacion: String) {
        _observacion.postValue(observacion)
    }

    fun onNumChipChange(numChip: String) {
        _numChip.postValue(numChip)
    }

    fun onColorAsigChange(colorAsig: String) {
        _colorAsig.postValue(colorAsig)
    }

    fun onImagenChange(imagen: ImageBitmap) {
        _imagen.postValue(imagen)
    }

    fun onTamanyoChange(tamanyo: String) {
        _tamanyo.postValue(tamanyo)
    }

    fun onSexoChange(sexo: String) {
        _sexo.postValue(sexo)
    }


    fun onDialogConfirm(navController: NavController) {
        showDialog = false
        navController.navigate(route = Destinations.Home.route)

    }

    fun onOpenDialog() {
        showDialog = true
    }

    fun onFinalizarPress(navController: NavController, context: Context) {
        viewModelScope.launch {
            val nombre = _nombre.value
            val numChip = _numChip.value
            val tipo = _tipo.value
            val colorAsig = _colorAsig.value
            _nombreEmpty.postValue(false)
            _numChipEmpty.postValue(false)
            _tipoEmpty.postValue(false)
            _colorEmpty.postValue(false)

            if (nombre.isNullOrBlank()) {
                _nombreEmpty.postValue(true)
            }
            if (numChip.isNullOrBlank()) {
                _numChipEmpty.postValue(true)
            }
            if (tipo.isNullOrBlank()) {
                _tipoEmpty.postValue(true)
            }
            if (colorAsig.isNullOrBlank()) {
                _colorEmpty.postValue(true)
            }
            if (_colorEmpty.value == false && _nombreEmpty.value == false && _numChipEmpty.value == false
                && _tipoEmpty.value == false
            ) {

                try {
                    val mascota = Mascota(
                        0, _nombre.value!!, _numChip.value!!,
                        _edad.value!!, ImageConverter.imageBitmapToByteArray(_imagen.value!!),
                        _tamanyo.value!!, _peso.value!!, _tipo.value!!,
                        _raza.value!!, _observacion.value!!, _colorAsig.value!!, _sexo.value!!
                    )
                    val response = mascotaRegisterUseCase.registroMascota(mascota)
                    if (response.ok) {
                        limpiarCampos()

                        Log.d("RegistroMascotaViewModel", "respuesta: $response")
                        Toast.makeText(context, "Mascota registrada", Toast.LENGTH_SHORT).show()
                        navController.navigate(route = Destinations.Home.route)
                    } else {
                        onOpenDialog()
                    }
                } catch (e: Exception) {
                    Log.d("RegistroMascotaViewModel", "error: $e")
                }

            }
        }
    }

    private fun limpiarCampos() {
        _nombre.postValue("")
        _numChip.postValue("")
        _tipo.postValue("")
        _colorAsig.postValue("")
        _tamanyo.postValue("")
        _peso.postValue(0.0)
        _observacion.postValue("")
        _sexo.postValue("")
        _raza.postValue("")
        _edad.postValue("")
    }
}