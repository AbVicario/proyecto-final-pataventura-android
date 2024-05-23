package com.example.pataventura.ui.screens.perfil_mascota

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
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
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaDeleteUseCase
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaGetUseCase
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilMascotaViewModel @Inject constructor(
    private val getMascotaUseCase: MascotaGetUseCase,
    private val updateMascotaUseCase: MascotaUpdateUseCase,
    private val deleteMascotaUseCase: MascotaDeleteUseCase
) : ViewModel() {

    private val _editMode = MutableLiveData<Boolean>()
    val editMode: LiveData<Boolean> = _editMode

    private val _mascota = MutableLiveData<Mascota>()
    val mascota: LiveData<Mascota> = _mascota

    var showDialog by mutableStateOf(false)
        private set
    var showDialogDelete by mutableStateOf(false)

    private val _nombreEmpty = MutableLiveData<Boolean>()
    val nombreEmpty: LiveData<Boolean> = _nombreEmpty
    private val _tipoEmpty = MutableLiveData<Boolean>()
    val tipoEmpty: LiveData<Boolean> = _tipoEmpty
    private val _numChipEmpty = MutableLiveData<Boolean>()
    val numChipEmpty: LiveData<Boolean> = _numChipEmpty
    private val _colorEmpty = MutableLiveData<Boolean>()
    val colorEmpty: LiveData<Boolean> = _colorEmpty


    private val _nombre = MutableLiveData("")
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
    private val _imagen = MutableLiveData(ImageBitmap(1, 1))
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
    fun onDialogDeleteConfirm(navController: NavController) {
        viewModelScope.launch {
        showDialogDelete = false
        deleteMascotaUseCase.deleteMascota(mascota.value!!.idMascota)
        navController.navigate(route = Destinations.Mascotas.route)
        }
    }
    fun onDialogDeleteDismiss(navController: NavController) {
        showDialogDelete = false
    }

    fun onOpenDialog() {
        showDialog = true
    }
    fun onOpenDeleteDialog() {
        showDialogDelete = true
    }


    fun setMascotaId(idMascota: Int) {
        viewModelScope.launch {
            val mascota = getMascotaUseCase.getMascota(idMascota)
            if(mascota != null) {
                _mascota.postValue(mascota!!)
                _nombre.postValue(mascota.nombre)
                _raza.postValue(mascota.raza)
                _sexo.postValue(mascota.sexo)
                _imagen.postValue(ImageConverter.byteArrayToImageBitmap(mascota.imagen!!))
                _peso.postValue(mascota.peso.toString())
                _edad.postValue(mascota.edad)
                _observacion.postValue(mascota.observacion)
                _numChip.postValue(mascota.numChip)
                _color.postValue(mascota.color)
                _tamanyo.postValue(mascota.tamanyo)
                _tipo.postValue(mascota.tipo)
            }

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

    fun onEditModeChange(editMode: Boolean) {
        _editMode.postValue(editMode)
    }

    fun onSave(navController: NavController) {
        val mascotaUpdate = mascota.value
        mascotaUpdate?.nombre = nombre.value!!
        mascotaUpdate?.raza = raza.value!!
        mascotaUpdate?.sexo = sexo.value!!
        mascotaUpdate?.imagen = ImageConverter.imageBitmapToByteArray(imagen.value!!)
        mascotaUpdate?.peso = peso.value!!.toDouble()
        mascotaUpdate?.edad = edad.value!!
        mascotaUpdate?.observacion = observacion.value!!
        mascotaUpdate?.numChip = numChip.value!!
        mascotaUpdate?.tamanyo = tamanyo.value!!
        mascotaUpdate?.tipo = tipo.value!!
        viewModelScope.launch {
            updateMascotaUseCase.updateMascota(mascotaUpdate!!)
            navController.navigate(route = Destinations.Mascotas.route)
        }
    }

    fun onDelete(navController: NavController) {
        viewModelScope.launch {
            onOpenDeleteDialog()
        }
    }

    fun validarCampos(navController: NavController, context: Context) {
        viewModelScope.launch {

            if (_numChip.value.isNullOrBlank()) {
                _numChipEmpty.postValue(true)
            } else {
                _numChipEmpty.postValue(false)
            }

            if (_tipo.value.isNullOrBlank()) {
                _tipoEmpty.postValue(true)
            } else {
                _tipoEmpty.postValue(false)
            }

            joinAll()

            if (_numChipEmpty.value == false && _tipoEmpty.value == false
            ) {
                try {
                    onSave(navController)
                    onEditModeChange(false)
                    Toast.makeText(context, "Mascota actualizada", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Error al actualizar la mascota", Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }

            }

        }
    }
}