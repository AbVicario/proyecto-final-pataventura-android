package com.example.pataventura.presentation.screens.loginCliente.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.pataventura.ui.theme.Tierra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Email") },
        leadingIcon = { Icon(Icons.Default.Email, null) },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra

        )
    )
}