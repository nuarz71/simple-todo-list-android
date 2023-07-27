package io.github.nuarz71.todolist.android.ui.screen.listtask

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.nuarz71.todolist.android.R
import io.github.nuarz71.todolist.android.domain.dto.TaskDto
import io.github.nuarz71.todolist.android.ui.components.EmptyTaskInfo
import io.github.nuarz71.todolist.android.ui.components.TaskListTile
import io.github.nuarz71.todolist.android.ui.navigation.LocalNavigatorProvider
import io.github.nuarz71.todolist.android.ui.navigation.TodoListNavigator
import io.github.nuarz71.todolist.android.ui.theme.TodoListTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ListTaskContent_Preview() {
    TodoListTheme {
        ListTaskContent(
            lazyListState = rememberLazyListState(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTaskScreen(
    navigator: TodoListNavigator = LocalNavigatorProvider.current,
    viewModel: ListTaskViewModel = hiltViewModel()
) {
    val lazyListState = rememberLazyListState()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(text = stringResource(R.string.app_name))
                }
            )
        },
        floatingActionButton = {
            if (tasks.isNotEmpty()) {
                ExtendedFloatingActionButton(
                    text = {
                        Text(text = stringResource(R.string.btn_add_task))
                    },
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(50)),
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = stringResource(R.string.btn_add_task)
                        )
                    },
                    onClick = {
                        navigator.toAddEditTaskScreen()
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        ListTaskContent(
            lazyListState = lazyListState,
            tasks = tasks,
            onGenerateTasksClick = viewModel::generateTasks,
            onDeleteItemClick = viewModel::deleteTask,
            onItemClick = { task ->
                navigator.toAddEditTaskScreen(id = task.id)
            },
            onAddTaskClick = {
                navigator.toAddEditTaskScreen()
            },
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
    
    LaunchedEffect(viewModel.snackbarMessage) {
        if (viewModel.snackbarMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(
                viewModel.snackbarMessage,
                duration = SnackbarDuration.Short
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListTaskContent(
    modifier: Modifier,
    lazyListState: LazyListState,
    tasks: List<TaskDto> = emptyList(),
    onItemClick: (TaskDto) -> Unit = {},
    onDeleteItemClick: (TaskDto) -> Unit = {},
    onAddTaskClick: () -> Unit = {},
    onGenerateTasksClick: () -> Unit = {}
) {
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(bottom = 84.dp),
        modifier = modifier
    ) {
        if (tasks.isEmpty()) {
            item {
                EmptyTaskInfo(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .padding(16.dp),
                    message = stringResource(R.string.empty_task_placeholder),
                    onAddClick = onAddTaskClick,
                    onGenerateClick = onGenerateTasksClick
                )
            }
        } else {
            items(tasks, key = { it.id }) { task ->
                TaskListTile(
                    task = task,
                    onClick = onItemClick,
                    onDeleteClick = onDeleteItemClick,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .animateItemPlacement()
                )
            }
        }
    }
}