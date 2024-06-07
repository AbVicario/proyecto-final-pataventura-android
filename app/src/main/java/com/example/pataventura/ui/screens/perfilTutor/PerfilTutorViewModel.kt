package com.example.pataventura.ui.screens.perfilTutor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.di.RoleHolder.rol
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Tutor
import com.example.pataventura.domain.useCase.ClearDBUseCase
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorUpdateUseCase
import com.example.pataventura.domain.useCase.tutorUseCase.TutorGetUseCase
import com.example.pataventura.domain.useCase.tutorUseCase.TutorUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilTutorViewModel @Inject constructor(
    private val tutorGetUseCase: TutorGetUseCase,
    private val cuidadorGetUseCase: CuidadorGetUseCase,
    private val tutorUpdateUseCase: TutorUpdateUseCase,
    private val cuidadorUpdateUseCase: CuidadorUpdateUseCase,
    private val clearDBUseCase: ClearDBUseCase
) : ViewModel() {

    private val rolObserver = Observer<String> { newValue -> newValue.lowercase() }
    var showDialog by mutableStateOf(false)
        private set

    private val _editMode = MutableLiveData<Boolean>()
    val editMode: LiveData<Boolean> = _editMode

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _imagen = MutableLiveData(ImageBitmap(1,1))
    val imagen: LiveData<ImageBitmap> = _imagen

    private val _alias = MutableLiveData<String>()
    val alias: LiveData<String> = _alias

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _isAlias = MutableLiveData<Boolean>()
    val isAlias: LiveData<Boolean> = _isAlias

    private val _isPhone = MutableLiveData<Boolean>()
    val isPhone: LiveData<Boolean> = _isPhone

    private val _isEmail = MutableLiveData<Boolean>()
    val isEmail: LiveData<Boolean> = _isEmail

    private val _isNotEmail = MutableLiveData<Boolean>()
    val isNotEmail: LiveData<Boolean> = _isNotEmail

    private val _isAddress = MutableLiveData<Boolean>()
    val isAddress: LiveData<Boolean> = _isAddress

    private var tutor: Tutor? = null
    private var cuidador: Cuidador? = null
    fun setTutor(tutor: Tutor) {
        this.tutor = tutor
    }

    fun setCuidador(cuidador: Cuidador) {
        this.cuidador = cuidador
    }

    fun clearDB(navController: NavController) {
        viewModelScope.launch {
            clearDBUseCase.clearDB()
            RoleHolder.setRol("")
            navController.navigate(Destinations.LoginCliente.route)
        }
    }

    fun printUserLaunch() {
        viewModelScope.launch {
            rol.observeForever(rolObserver)
            if (rol.value == "tutor") {
                printTutor()
            } else {
                printCuidador()

            }
        }
    }

    private suspend fun printTutor() {
        val user = tutorGetUseCase.getTutor()
        Log.d("user", user.toString())

        if (user != null) {
            setTutor(user)
            onValueChangeAlias(user.alias)
            onValueChangePhone(user.telefono)
            onValueChangeEmail(user.email)
            onValueChangeAddress(user.direccion)
            _imagen.postValue(ImageConverter.byteArrayToImageBitmap(user.imagen))
            _name.postValue(user.nombre)

            _isAlias.postValue(true)
            _isEmail.postValue(true)
            _isNotEmail.postValue(true)
            _isPhone.postValue(true)
            _isAddress.postValue(true)

        }
    }

    private suspend fun printCuidador() {
        val user = cuidadorGetUseCase.getCuidador()

        if (user != null) {
            setCuidador(user)
            onValueChangeAlias(user.alias)
            onValueChangePhone(user.telefono)
            onValueChangeEmail(user.email)
            onValueChangeAddress(user.direccion)
            _imagen.postValue(ImageConverter.byteArrayToImageBitmap(user.imagen))
            _name.postValue(user.nombre)

            _isAlias.postValue(true)
            _isEmail.postValue(true)
            _isNotEmail.postValue(true)
            _isPhone.postValue(true)
            _isAddress.postValue(true)
        }
    }

    fun onValueChangeEditMode(edit: Boolean) {
        _editMode.postValue(edit)
    }

    fun onValueChangeAlias(alias: String) {
        _alias.postValue(alias)
    }

    fun onValueChangePhone(phone: String) {
        _phone.postValue(phone)
    }

    fun onValueChangeAddress(address: String) {
        _address.postValue(address)
    }

    fun onValueChangeEmail(email: String) {
        _email.postValue(email)
    }

    fun onValueChangeImage(image: ImageBitmap) {
        _imagen.value = image
    }

    fun onDialogConfirm(navController: NavController) {
        showDialog = false
        navController.navigate(route = Destinations.LoginCliente.route)

    }

    fun onOpenDialog() {
        showDialog = true
    }

    suspend fun updateUser(navController: NavController) {
        rol.observeForever(rolObserver)
        if (rol.value == "tutor") {
            updateTutor(navController)
        } else {
            updateCuidador(navController)
        }

    }

    private suspend fun updateTutor(navController: NavController) {
        try {
            val response = tutorUpdateUseCase.updateTutor(
                Tutor(
                    tutor!!.idUsuario,
                    _email.value!!,
                    tutor!!.password,
                    _phone.value!!,
                    tutor!!.nombre,
                    tutor!!.apellido,
                    ImageConverter.imageBitmapToByteArray(_imagen.value!!),
                    _alias.value!!,
                    _address.value!!
                )
            )
            if (response.ok) {
                navController.navigate("home")
            } else {
                onOpenDialog()
            }
        } catch (e: Exception) {
            throw e
        }


    }

    private suspend fun updateCuidador(navController: NavController) {
        try {
            val response = cuidadorUpdateUseCase.updateCuidador(
                Cuidador(
                    cuidador!!.idUsuario,
                    _email.value!!,
                    cuidador!!.password,
                    _phone.value!!,
                    cuidador!!.nombre,
                    cuidador!!.apellido,
                    ImageConverter.imageBitmapToByteArray(_imagen.value!!),
                    _alias.value!!,
                    _address.value!!
                )
            )
            if (response.ok) {
                navController.navigate("home")
            } else {
                onOpenDialog()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun validarCampos(navController: NavController) {
        viewModelScope.launch {
            val regex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")


            if (_alias.value.isNullOrBlank()) {
                _isAlias.postValue(false)
            }

            if (_email.value.isNullOrBlank()) {
                _isEmail.postValue(false)
            } else if (!regex.matches(email.value.toString())) {
                _isNotEmail.postValue(false)
            }

            if (_phone.value.isNullOrBlank()) {
                _isPhone.postValue(false)
            }

            if (_address.value.isNullOrBlank()) {
                _isAddress.postValue(false)
            }

            joinAll()

            if (_isAlias.value == true && _isAddress.value == true && _isPhone.value == true && _isEmail.value == true
                && _isNotEmail.value == true
            ) {
                updateUser(navController)
            }
        }
    }
}