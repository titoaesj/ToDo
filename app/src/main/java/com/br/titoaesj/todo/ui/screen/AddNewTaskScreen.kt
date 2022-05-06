package com.br.titoaesj.todo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.br.titoaesj.todo.R
import com.br.titoaesj.todo.data.ConstTestTAG
import com.br.titoaesj.todo.data.Task
import com.br.titoaesj.todo.ui.theme.OrageYellow100
import com.br.titoaesj.todo.ui.theme.OrageYellow700
import com.br.titoaesj.todo.ui.theme.Shapes
import com.br.titoaesj.todo.ui.theme.ToDoTheme
import kotlinx.coroutines.delay

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */

@Composable
fun AddNewTaskScreen(viewModel: TaskViewModel, navigation: NavHostController) {

    AddNewTaskContent(
        onClickAddTask = { task ->
            viewModel.addTask(task = task)
            navigation.popBackStack()
        },
        onBackPressed = {
            navigation.popBackStack()
        }
    )
}

@Composable
fun AddNewTaskContent(
    onClickAddTask: (Task) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed,
                        modifier = Modifier.testTag(
                            ConstTestTAG.AddNewTaskScreen.TopAppBarBackPressTAG
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_label)
                        )
                    }
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        FromContent(onClickAddTask = onClickAddTask)
    }
}

@Composable
fun FromContent(onClickAddTask: (Task) -> Unit) {

    var taskNameState by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(focusManager) {
        delay(500L)
        focusManager.moveFocus(focusDirection = FocusDirection.Down)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { focusManager.clearFocus() }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .background(color = OrageYellow100)
        ) {
            TextField(
                value = taskNameState,
                shape = Shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = Color.Transparent,
                    cursorColor = OrageYellow700,
                    focusedIndicatorColor = OrageYellow100,
                    unfocusedIndicatorColor = OrageYellow100,
                    disabledIndicatorColor = OrageYellow100,
                ),
                placeholder = { Text(text = stringResource(id = R.string.form_task_label)) },
                onValueChange = { taskNameState = it },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                    .testTag(ConstTestTAG.AddNewTaskScreen.TextFieldNewTaskTAG)
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            onClick = { onClickAddTask(Task(description = taskNameState)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                .testTag(ConstTestTAG.AddNewTaskScreen.AddButtonTAG)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_task_label),
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.add_label),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewTaskLightPreview() {
    ToDoTheme(darkTheme = false) {
        AddNewTaskContent(onClickAddTask = {}, onBackPressed = {})
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewTaskDarkPreview() {
    ToDoTheme(darkTheme = true) {
        AddNewTaskContent(onClickAddTask = {}, onBackPressed = {})
    }
}