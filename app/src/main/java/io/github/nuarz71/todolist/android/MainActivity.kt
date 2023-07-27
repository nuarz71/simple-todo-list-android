package io.github.nuarz71.todolist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.github.nuarz71.todolist.android.ui.navigation.TodoListNavGraph
import io.github.nuarz71.todolist.android.ui.theme.TodoListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                TodoListNavGraph()
            }
        }
    }
}