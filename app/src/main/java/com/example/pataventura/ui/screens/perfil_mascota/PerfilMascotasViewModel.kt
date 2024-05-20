package com.example.pataventura.ui.screens.perfil_mascota

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaGetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilMascotaViewModel @Inject constructor(
    private val getMascotaUseCase: MascotaGetUseCase
) : ViewModel() {

    private val _editMode = MutableLiveData<Boolean>(false)
    val editMode: LiveData<Boolean> = _editMode

    private val _mascota = MutableLiveData<Mascota>()
    val mascota: LiveData<Mascota> = _mascota

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


    private val _nombre = MutableLiveData<String>("")
    val nombre: LiveData<String> = _nombre
    private val _color = MutableLiveData<String>("")
    val color: LiveData<String> = _color
    private val _tamanyo = MutableLiveData<String>()
    val tamanyo: LiveData<String> = _tamanyo
    private val _sexo = MutableLiveData<String>()
    val sexo: LiveData<String> = _sexo
    private val _tipo = MutableLiveData<String>()
    val tipo: LiveData<String> = _tipo
    private val _raza = MutableLiveData<String>()
    val raza: LiveData<String> = _raza
    private val _edad = MutableLiveData<String>()
    val edad: LiveData<String> = _edad
    private val _peso = MutableLiveData<String>()
    val peso: LiveData<String> = _peso
    private val _observacion = MutableLiveData<String>()
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

    fun onPesoChange(peso: String) {
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


    fun setMascotaId(idMascota: Int) {
        viewModelScope.launch {
            val mascota = getMascotaUseCase.getMascota(idMascota)

            _mascota.postValue(mascota)
            _nombre.postValue(mascota.nombre)
            _raza.postValue(mascota.raza)
            _sexo.postValue(mascota.sexo)
            _imagen.postValue(ImageConverter.byteArrayToImageBitmap(mascota.imagen!!))
            _peso.postValue(mascota.peso.toString())
            _edad.postValue(mascota.edad)
            _observacion.postValue(mascota.observacion)
            _numChip.postValue(mascota.numChip)
            _color.postValue(mascota.color)

        }
    }


    fun obtenerColor(color: String): Color {
        when (color.lowercase()) {
            "amarillo" -> return Color.Yellow
            "rojo" -> return Color.Red
            "azul" -> return Color.Blue
            "verde" -> return Color.Green
            "negro" -> return Color.Black
            else -> return Color.White
        }
    }
}