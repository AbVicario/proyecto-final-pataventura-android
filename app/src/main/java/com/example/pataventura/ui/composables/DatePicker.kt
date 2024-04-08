package com.example.pataventura.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.ui.theme.TierraVerde
import com.example.pataventura.ui.theme.Verde
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import androidx.compose.material3.*
import com.example.pataventura.ui.theme.MyLightColorScheme




@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DatePickerWithDialog(
    value: LocalDate?,
    dateFormatter: (LocalDate) -> String,
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    dateValidator: (Long) -> Boolean = { true },
    onChange: (LocalDate?) -> Unit
) {

    val customDatePickerColors = DatePickerDefaults.colors(
        containerColor = Color(0xFFebeaf3),
        titleContentColor = Verde,
        headlineContentColor = Verde,
        weekdayContentColor = Verde,
        subheadContentColor = Verde,
        yearContentColor = Verde,
        currentYearContentColor = Verde,
        selectedYearContentColor = Verde,
        selectedYearContainerColor = Verde.copy(0.2f),
        dayContentColor = Verde,
        disabledDayContentColor = Verde,
        selectedDayContentColor = Verde,
        disabledSelectedDayContentColor = Verde,
        selectedDayContainerColor = Verde.copy(alpha = 0.2f),
        disabledSelectedDayContainerColor = Verde,
        todayContentColor = Verde,
        todayDateBorderColor = Verde,
        dayInSelectionRangeContainerColor = Verde,
        dayInSelectionRangeContentColor = Verde,
    )

    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        focusedTextColor = Verde,
        unfocusedTextColor = Verde,
        disabledTextColor = Verde,
        errorTextColor = Color.Red,
        containerColor = Color.White,
        errorContainerColor = Color.Red.copy(0.1f),
        cursorColor = Verde,
        errorCursorColor = Color.Red,
        focusedIndicatorColor = Verde,
        unfocusedIndicatorColor  = Verde,
        disabledIndicatorColor = Color.White,
        errorIndicatorColor = Color.Red,
        focusedLeadingIconColor = Verde,
        unfocusedLeadingIconColor = Verde,
        disabledLeadingIconColor = LightGray,
        errorLeadingIconColor = Color.Red,
        focusedTrailingIconColor = Verde,
        unfocusedTrailingIconColor = Verde,
        disabledTrailingIconColor =  LightGray,
        errorTrailingIconColor = Color.Red,
        focusedLabelColor = Verde,
        unfocusedLabelColor = Verde,
        disabledLabelColor = LightGray,
        errorLabelColor = Color.Red,
        focusedPlaceholderColor = Verde,
        unfocusedPlaceholderColor = Verde,
        disabledPlaceholderColor = Color.Gray,
        errorPlaceholderColor = Color.Red,
        focusedSupportingTextColor = Verde,
        unfocusedSupportingTextColor = Verde,
        disabledSupportingTextColor = LightGray,
        errorSupportingTextColor = Color.Red,
        focusedPrefixColor = Verde,
        unfocusedPrefixColor = Verde,
        disabledPrefixColor = LightGray,
        errorPrefixColor = Color.Red ,
        focusedSuffixColor = Verde,
        unfocusedSuffixColor = Verde ,
        disabledSuffixColor = LightGray,
        errorSuffixColor = Color.Red)
    var openDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value?.atStartOfDay()?.toEpochSecond(ZoneOffset.UTC)
            ?.times(1000)
    )

    Box {
        TextField(
            value = value?.let(dateFormatter).orEmpty(),
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = placeholder ?: { DatePickerDefaults.DatePickerTitle(datePickerState) },
            colors =  customTextFieldColors,
            trailingIcon = {
                Icon(
                    Icons.Default.EditCalendar,
                    contentDescription = "", tint = Verde
                )
            })

        Box(
            Modifier
                .clickable(enabled = enabled) { openDialog = true }
                .matchParentSize()

        ) {
        }
    }

    if (openDialog) {
        val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        onChange(datePickerState.selectedDateMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        })
                    },
                    enabled = enabled && confirmEnabled
                ) {
                    Text(stringResource(id = android.R.string.ok), color = Verde)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text(stringResource(id = android.R.string.cancel), color = Verde)
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                dateValidator = dateValidator,
                colors = customDatePickerColors
            )
        }
    }
}