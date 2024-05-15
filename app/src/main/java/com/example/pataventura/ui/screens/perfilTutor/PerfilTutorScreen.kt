package com.example.pataventura.ui.screens.perfilTutor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.screens.loginCliente.LoginClienteViewModel
import com.example.pataventura.ui.screens.perfilTutor.composables.BodyPerfilTutor
import com.example.pataventura.ui.screens.perfilTutor.composables.HeaderPerfilTutor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilTutorScreen(
    navController: NavController,
    perfilTutorViewModel: PerfilTutorViewModel
) {
    var selectedIcon by remember { mutableStateOf(Icons.Default.Person) }
    val editMode by perfilTutorViewModel.editMode.observeAsState(false)
    val image by perfilTutorViewModel.image.observeAsState()

    LaunchedEffect (Unit){
        perfilTutorViewModel.printUserLaunch()
    }
    Scaffold(bottomBar = {
        BottomBar(selectedIcon, navController) {
            selectedIcon = it
        }
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderPerfilTutor(
                editMode,
                image,
                perfilTutorViewModel
            )
            Spacer(modifier = Modifier.size(20.dp))
            BodyPerfilTutor(
                editMode, image, perfilTutorViewModel, navController
            )
        }

    }
}
