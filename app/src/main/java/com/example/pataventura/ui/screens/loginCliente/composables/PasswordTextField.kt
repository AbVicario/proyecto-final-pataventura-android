package com.example.pataventura.ui.screens.loginCliente.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.pataventura.ui.theme.Tierra

@Composable
fun PasswordTextField(password: String, onTextChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = { onTextChange(it) },
        singleLine = true,
        placeholder = { Text(text = "Password") },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "Toggle password visibility",
                )
            }
        },
        leadingIcon = { Icon(Icons.Filled.Lock, null) },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedTrailingIconColor = Tierra,
            unfocusedTrailingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra


        )
    )
}