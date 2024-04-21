package com.example.pataventura.ui.screens.perfilTutor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.screens.perfilTutor.composables.BodyPerfilTutor
import com.example.pataventura.ui.screens.perfilTutor.composables.HeaderPerfilTutor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilTutorScreen(
    navController: NavController,
    perfilTutorViewModel: PerfilTutorViewModel)
{
    var selectedIcon by remember { mutableStateOf(Icons.Default.Person) }
    Scaffold( bottomBar = {
        BottomBar(selectedIcon){
            selectedIcon = it
        }
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HeaderPerfilTutor()
            Spacer(modifier = Modifier.size(20.dp))
            BodyPerfilTutor(perfilTutorViewModel = perfilTutorViewModel,
                navController = navController
            )
        }
    }

}