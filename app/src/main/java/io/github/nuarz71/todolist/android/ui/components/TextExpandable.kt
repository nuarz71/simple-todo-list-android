package io.github.nuarz71.todolist.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TextExpandable_Preview() {
    Surface {
        TextExpandable(text = "Sample of \n expandable")
    }
}

@Composable
fun TextExpandable(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    minLines: Int = 1,
) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    var allowExpandable by rememberSaveable { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    
    LaunchedEffect(allowExpandable, text) {
        val layout = textLayoutResult
        allowExpandable = layout?.let {
            it.hasVisualOverflow || it.lineCount > minLines
        } ?: false
    }
    
    Row(modifier = modifier) {
        Text(
            text = text,
            onTextLayout = { textLayoutResult = it },
            style = style,
            maxLines = if (expanded) Int.MAX_VALUE else minLines,
            overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(top = if (allowExpandable) 2.dp else 0.dp)
        )
        if (allowExpandable) {
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(if (expanded) -90F else 90F)
                    .clickable { expanded = expanded.not() }
            )
        }
    }
}