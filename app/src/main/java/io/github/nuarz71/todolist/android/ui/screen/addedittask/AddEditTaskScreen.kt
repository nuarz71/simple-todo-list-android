package io.github.nuarz71.todolist.android.ui.screen.addedittask

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.nuarz71.todolist.android.R
import io.github.nuarz71.todolist.android.ui.components.DueDateOutlinedTextField
import io.github.nuarz71.todolist.android.ui.components.SimpleDatePickerDialog
import io.github.nuarz71.todolist.android.ui.navigation.LocalNavigatorProvider
import io.github.nuarz71.todolist.android.ui.navigation.TodoListNavigator
import io.github.nuarz71.todolist.android.ui.theme.TodoListTheme
import java.time.LocalDateTime
import java.time.ZoneOffset

@Preview(showSystemUi = true)
@Composable
private fun AddEditTaskContent_Preview() {
    TodoListTheme {
        AddEditTaskContent(
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    navigator: TodoListNavigator = LocalNavigatorProvider.current,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (viewModel.id > 0) {
                            stringResource(id = R.string.title_edit_task)
                        } else {
                            stringResource(id = R.string.title_add_task)
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.btn_back)
                        )
                    }
                },
                actions = {
                    if (viewModel.id > 0) {
                        IconButton(
                            onClick = viewModel::deleteTask
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = stringResource(id = R.string.desc_delete_the_task)
                            )
                        }
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            Button(
                onClick = viewModel::save,
                enabled = uiState.title.length > 4 && uiState.dueDate != null,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.btn_save))
            }
        }
    ) {
        AddEditTaskContent(
            loading = uiState.isLoading,
            title = uiState.title,
            description = uiState.description,
            dueDate = uiState.dueDate,
            setTitle = viewModel::setTitle,
            setDescription = viewModel::setDescription,
            setDueDate = viewModel::setDueDate,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
    LaunchedEffect(uiState.snackBarMessage) {
        if (uiState.snackBarMessage.isNullOrBlank().not()) {
            snackbarHostState.showSnackbar(
                message = uiState.snackBarMessage.orEmpty(),
                duration = SnackbarDuration.Short
            )
        }
    }
    LaunchedEffect(uiState.saved) {
        if (uiState.saved) {
            navigator.popBackStack()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    loading: Boolean = false,
    title: String = "",
    description: String = "",
    dueDate: LocalDateTime? = null,
    setTitle: (String) -> Unit = {},
    setDescription: (String) -> Unit = {},
    setDueDate: (Long?) -> Unit = {},
) {
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.hint_task_title),
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            value = title,
            onValueChange = setTitle,
            textStyle = MaterialTheme.typography.titleMedium,
            placeholder = {
                Text(text = stringResource(R.string.placeholder_enter_task_title))
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.hint_task_due_date),
            style = MaterialTheme.typography.labelMedium
        )
        DueDateOutlinedTextField(
            onClick = {
                showDatePicker = showDatePicker.not()
            },
            dueDate = dueDate,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.hint_task_description),
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            value = description,
            onValueChange = setDescription,
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(text = stringResource(R.string.placeholder_enter_task_description))
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
    }
    
    SimpleDatePickerDialog(
        onDismissRequest = { showDatePicker = showDatePicker.not() },
        showDialog = showDatePicker,
        onValueChange = setDueDate,
        initialSelectedDateMillis = dueDate?.toInstant(ZoneOffset.UTC)
            ?.toEpochMilli()
    )
    
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
