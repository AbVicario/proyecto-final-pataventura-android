package com.example.pataventura.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
    fun IconBack(/*onClick: () -> Unit*/) {
        Box(Modifier.size(50.dp)
            .clickable {  }
        ){
            Icon(
                Icons.Default.KeyboardArrowLeft, "Atras",
                tint = Color.White,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
