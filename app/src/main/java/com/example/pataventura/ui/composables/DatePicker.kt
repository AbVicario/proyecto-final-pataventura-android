package com.example.pataventura.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pataventura.ui.theme.Verde
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    isInicio: Boolean,
    servicio: String,
    value: LocalDateTime?,
    dateFormatter: (LocalDateTime) -> String,
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    dateValidator: (Long) -> Boolean = { true },
    minDateTime: LocalDateTime,
    onChange: (LocalDateTime?) -> Unit
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
        focusedTextColor = Black,
        unfocusedTextColor = Black,
        disabledTextColor = Gray,
        errorTextColor = Color.Red,
        containerColor = Color.White,
        errorContainerColor = Color.Red.copy(0.1f),
        cursorColor = Verde,
        errorCursorColor = Color.Red,
        focusedIndicatorColor = Verde,
        unfocusedIndicatorColor = Verde,
        disabledIndicatorColor = Color.White,
        errorIndicatorColor = Color.Red,
        focusedLeadingIconColor = Verde,
        unfocusedLeadingIconColor = Verde,
        disabledLeadingIconColor = LightGray,
        errorLeadingIconColor = Color.Red,
        focusedTrailingIconColor = Verde,
        unfocusedTrailingIconColor = Verde,
        disabledTrailingIconColor = LightGray,
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
        errorPrefixColor = Color.Red,
        focusedSuffixColor = Verde,
        unfocusedSuffixColor = Verde,
        disabledSuffixColor = LightGray,
        errorSuffixColor = Color.Red
    )

    var openDatePickerDialog by remember { mutableStateOf(false) }
    var openTimePickerDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value?.toLocalDate()?.atStartOfDay(ZoneOffset.UTC)
            ?.toEpochSecond()?.times(1000)
    )

    val minDateInMillis = minDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
    val customDateValidator: (Long) -> Boolean = { dateInMillis ->
        dateValidator(dateInMillis) && dateInMillis >= minDateInMillis
    }

    Box {
        TextField(
            value = value?.let(dateFormatter).orEmpty(),
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = placeholder ?: { DatePickerDefaults.DatePickerTitle(datePickerState) },
            colors = customTextFieldColors,
            trailingIcon = {
                Icon(
                    Icons.Default.EditCalendar,
                    contentDescription = "",
                    Modifier.clickable(enabled = enabled) { openDatePickerDialog = true },
                    tint = Verde
                )
            }
        )
    }

    if (openDatePickerDialog) {
        val confirmDateEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = { openDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDatePickerDialog = false
                        selectedDate = datePickerState.selectedDateMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        }
                        openTimePickerDialog = true
                    },
                    enabled = enabled && confirmDateEnabled
                ) {
                    Text(stringResource(id = android.R.string.ok), color = Verde)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDatePickerDialog = false
                    }
                ) {
                    Text(stringResource(id = android.R.string.cancel), color = Verde)
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                dateValidator = customDateValidator,
                colors = customDatePickerColors
            )
        }
    }

    if (openTimePickerDialog) {
        var selectedHour by remember { mutableStateOf(value?.hour ?: 0) }
        var selectedMinute by remember { mutableStateOf(value?.minute ?: 0) }

        Dialog(onDismissRequest = { openTimePickerDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Select Time")

                    Spacer(modifier = Modifier.height(16.dp))
                    if (isInicio) {
                        TimePicker(
                            selectedHour = selectedHour,
                            selectedMinute = selectedMinute,
                            onHourChanged = { selectedHour = it },
                            onMinuteChanged = { selectedMinute = it }
                        )
                    }
                    if (servicio.lowercase() == "paseo") {
                        TimePicker(
                            selectedHour = selectedHour,
                            selectedMinute = selectedMinute,
                            onHourChanged = { selectedHour = it },
                            onMinuteChanged = { selectedMinute = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                openTimePickerDialog = false
                            }
                        ) {
                            Text(stringResource(id = android.R.string.cancel), color = Verde)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        TextButton(
                            onClick = {
                                openTimePickerDialog = false
                                selectedTime = LocalTime.of(selectedHour, selectedMinute)
                                val selectedDateTime = selectedDate?.let { date ->
                                    selectedTime?.let { time ->
                                        LocalDateTime.of(date, time)
                                    }
                                }
                                if (selectedDateTime != null && selectedDateTime.isAfter(minDateTime)) {
                                    onChange(selectedDateTime)
                                } else {
                                    // Handle case where the selected date and time are before minDateTime
                                    // Show an error or reset the selection
                                    onChange(null)
                                }
                            }
                        ) {
                            Text(stringResource(id = android.R.string.ok), color = Verde)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimePicker(
    selectedHour: Int,
    selectedMinute: Int,
    onHourChanged: (Int) -> Unit,
    onMinuteChanged: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Hour")
            NumberPicker(
                value = selectedHour,
                range = 0..23,
                onValueChange = onHourChanged
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Minute")
            NumberPicker(
                value = selectedMinute,
                range = 0..59,
                onValueChange = onMinuteChanged
            )
        }
    }
}

@Composable
fun NumberPicker(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = value.toString(),
        onValueChange = {
            val newValue = it.toIntOrNull() ?: value
            if (newValue in range) {
                onValueChange(newValue)
            }
        },
        interactionSource = interactionSource,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            color = LocalContentColor.current
        ),
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
            .padding(4.dp)
    )
}
