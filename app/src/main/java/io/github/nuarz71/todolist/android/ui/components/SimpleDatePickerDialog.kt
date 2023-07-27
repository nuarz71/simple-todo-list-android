package io.github.nuarz71.todolist.android.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.nuarz71.todolist.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDatePickerDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    showDialog: Boolean,
    onValueChange: (Long?) -> Unit,
    initialSelectedDateMillis: Long? = null,
    datePickerState: DatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis
    ),
    showToggleMode: Boolean = false
) {
    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                        datePickerState.selectedDateMillis.let(onValueChange)
                    },
                    enabled = datePickerState.selectedDateMillis != null
                ) {
                    Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(R.string.btn_done))
                }
            },
            modifier = modifier
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = showToggleMode
            )
        }
    }
}