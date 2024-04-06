package com.example.pataventura.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.screens.home.composables.BodyHome
import com.example.pataventura.ui.screens.home.composables.HeaderHome


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    Scaffold( bottomBar = {
        BottomBar()
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween,
        ) {
            HeaderHome()
            BodyHome()
        }
    }

}