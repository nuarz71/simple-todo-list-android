package io.github.nuarz71.todolist.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.nuarz71.todolist.android.R
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import io.github.nuarz71.todolist.android.utils.DateTimeFormatterUtils
import io.github.nuarz71.todolist.android.utils.extension.ifNotBlank
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
private fun TaskListTile_Preview() {
    Surface {
        TaskListTile(
            modifier = Modifier.fillMaxWidth(), task = TaskDto(
                id = 1,
                title = "Sample",
                description = "Descriptions",
                dueDate = LocalDateTime.now()
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListTile(
    modifier: Modifier,
    task: TaskDto,
    onClick: (TaskDto) -> Unit = {},
    onDeleteClick: (TaskDto) -> Unit = {}
) {
    Card(
        onClick = { onClick(task) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        modifier = modifier
    ) {
        Box {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 56.dp)
                    .align(Alignment.CenterStart)
            )
            IconButton(
                onClick = { onDeleteClick(task) },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(R.string.desc_delete_the_task)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                6.dp, alignment = Alignment.Start
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = stringResource(R.string.desc_due_date),
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    ) {
                        append(stringResource(id = R.string.hint_task_due_date).plus(" : "))
                    }
                    append(
                        task.dueDate.format(DateTimeFormatterUtils.OF_PATTERN_EEEE_DD_MMM_YYYY)
                    )
                },
                style = MaterialTheme.typography.labelSmall.copy(fontStyle = FontStyle.Italic),
            )
        }
        task.description.ifNotBlank {
            TextExpandable(
                text = it,
                minLines = 2,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 10.dp, top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}