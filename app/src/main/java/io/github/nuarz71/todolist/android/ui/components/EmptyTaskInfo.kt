package io.github.nuarz71.todolist.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.nuarz71.todolist.android.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmptyTaskInfo_Preview() {
    Surface {
        EmptyTaskInfo(
            modifier = Modifier.fillMaxSize(),
            message = stringResource(id = R.string.empty_task_placeholder)
        )
    }
}

@Composable
fun EmptyTaskInfo(
    modifier: Modifier,
    message: String,
    onAddClick: () -> Unit = {},
    onGenerateClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Rounded.Info,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(R.string.desc_empty_task_info),
            modifier = Modifier.size(56.dp)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        ElevatedButton(
            onClick = { onAddClick() },
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(id = R.string.btn_add_task)
            )
            Text(text = stringResource(id = R.string.btn_add_task))
        }
        Text(text = "or", style = MaterialTheme.typography.labelSmall)
        OutlinedButton(
            onClick = { onGenerateClick() },
        ) {
            Text(text = stringResource(R.string.btn_generate_dummy_tasks))
        }
    }
}