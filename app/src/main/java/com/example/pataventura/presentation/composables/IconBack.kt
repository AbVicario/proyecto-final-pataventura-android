package com.example.pataventura.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable


    @Composable
    fun IconBack(/*onClick: () -> Unit*/) {
        Icon(
            Icons.Default.KeyboardArrowLeft, "Atras",
            //modifier = Modifier.clickable { onClick() }
        )
    }
