package com.br.titoaesj.todo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.br.titoaesj.todo.R
import com.br.titoaesj.todo.data.Task
import com.br.titoaesj.todo.ui.theme.Shapes

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */

@Composable
fun AddNewTaskScreen(viewModel: TaskViewModel, navigation : NavHostController) {

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
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) {
        FromContent(onClickAddTask = onClickAddTask)
    }
}

@Composable
fun FromContent(onClickAddTask: (Task) -> Unit) {

    var taskNameState by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = taskNameState,
            shape = Shapes.medium,
            placeholder = { Text(text = stringResource(id = R.string.form_task_label)) },
            onValueChange = { taskNameState = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 16.dp))
        Button(
            onClick = { onClickAddTask(Task(description = taskNameState)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
        ) {
            Icon(
                Icons.Filled.Add, contentDescription = "Adiciona atividade",
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(text = "Adicionar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewTaskPreview() {
    AddNewTaskContent(onClickAddTask = {}, onBackPressed = {})
}