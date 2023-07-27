package io.github.nuarz71.todolist.android.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.nuarz71.todolist.android.R
import io.github.nuarz71.todolist.android.utils.DateTimeFormatterUtils
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
private fun DueDateOutlinedTextField_Preview() {
    Surface {
        DueDateOutlinedTextField(onClick = { })
    }
}

@Composable
fun DueDateOutlinedTextField(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    dueDate: LocalDateTime? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    LaunchedEffect(isPressed) {
        if (isPressed) {
            onClick()
        }
    }
    OutlinedTextField(
        value = dueDate?.format(DateTimeFormatterUtils.OF_PATTERN_EEEE_DD_MMM_YYYY)
            ?: stringResource(R.string.placeholder_task_select_due_date),
        readOnly = true,
        onValueChange = {},
        singleLine = true,
        interactionSource = interactionSource,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = stringResource(id = R.string.desc_due_date)
            )
        },
        modifier = modifier
    )
}