package com.br.titoaesj.todo.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.br.titoaesj.todo.R
import com.br.titoaesj.todo.data.Task
import com.br.titoaesj.todo.navigation.Screen
import com.br.titoaesj.todo.ui.theme.Shapes

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */


@Composable
fun MainScreen(
    viewModel: TaskViewModel,
    navigation: NavHostController
) {
    MainContent(
        taskList = viewModel.tasks,
        addNewTaskSegue = {
            navigation.navigate(Screen.AddNewTaskcreen.router)
        },
        updateTaskStatus = { task ->
            task.isDone = !task.isDone
            viewModel.updateTask(task = task)
        }
    )
}

@Composable
fun MainContent(
    taskList: List<Task>,
    addNewTaskSegue: () -> Unit,
    updateTaskStatus: (Task) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        floatingActionButton = { AddTaskFab(onClick = addNewTaskSegue) }
    ) {
        ListTaskContent(taskList = taskList, updateTaskStatus = updateTaskStatus)
    }
}

@Composable
fun AddTaskFab(onClick: () -> Unit) {
    FloatingActionButton(shape = Shapes.small, onClick = onClick) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "adicionar tarefa")
    }
}

@Composable
fun TaskItemCard(task: Task, updateTaskStatus: (Task) -> Unit) {

    val checkedState = remember { mutableStateOf(task.isDone) }

    val constraints = ConstraintSet {

        val descriptionConst = createRefFor("description")
        val checkboxConst = createRefFor("checkbox")

        val guidelineH = createGuidelineFromAbsoluteRight(0.1f)

        constrain(descriptionConst) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(guidelineH, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(checkboxConst) {
            top.linkTo(descriptionConst.top)
            bottom.linkTo(descriptionConst.bottom)
            start.linkTo(guidelineH)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

    }

    Card(
        elevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {

        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = task.description,
                textAlign = TextAlign.Start,
                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier.layoutId("description")
            )

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    updateTaskStatus(task)
                    checkedState.value = task.isDone
                },
                modifier = Modifier.layoutId("checkbox")
            )

        }


    }
}

@Composable
fun ListTaskContent(taskList: List<Task>, updateTaskStatus: (Task) -> Unit) {

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(taskList) { task ->
            TaskItemCard(task = task, updateTaskStatus = updateTaskStatus)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {

    val tasks: List<Task> = listOf(
        Task(description = "Lavar as roupas", isDone = true),
        Task(description = "Limpar a casa", isDone = true),
        Task(description = "Passar na feira comprar Banana, Laranja, Manga e Maracujá"),
        Task(description = "Consulta dentista 01/01/2022 às 15h:30m")
    )

    MainContent(
        taskList = tasks,
        addNewTaskSegue = { },
        updateTaskStatus = { }
    )
}