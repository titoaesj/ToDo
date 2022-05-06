package com.br.titoaesj.todo.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
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
import com.br.titoaesj.todo.data.ConstTestTAG
import com.br.titoaesj.todo.data.Task
import com.br.titoaesj.todo.navigation.Screen
import com.br.titoaesj.todo.ui.theme.BlueGray900
import com.br.titoaesj.todo.ui.theme.Shapes
import com.br.titoaesj.todo.ui.theme.ToDoTheme

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

    var isContextualMenuMutableState by remember { mutableStateOf(false) }
    val itemsForRemoveMutableState = remember { mutableStateListOf<Task>() }

    MainContent(
        isContextualMenu = isContextualMenuMutableState,
        itemsForRemoveSize = itemsForRemoveMutableState.size,
        taskList = viewModel.tasks,
        addNewTaskOnAction = {
            navigation.navigate(Screen.AddNewTaskcreen.router)
        },
        updateTaskStatusOnAction = { task ->
            task.isDone = !task.isDone
            viewModel.updateTask(task = task)
        },
        removeTasksOnAction = {
            viewModel.removeTasks(tasks = itemsForRemoveMutableState.toList())

        },
        updateContextualMenuOnAction = { newContextualMenuState ->
            isContextualMenuMutableState = newContextualMenuState
        },
        clearContextualMenuOnAction = {
            itemsForRemoveMutableState.clear()
        },
        addNewItemForRmoveOnAction = { newTask ->
            if (itemsForRemoveMutableState.contains(newTask)) {
                itemsForRemoveMutableState.remove(newTask)
            } else {
                itemsForRemoveMutableState.add(newTask)
            }
        },
        itemForRemoveIsCheck = { task ->
            itemsForRemoveMutableState.contains(task)
        }
    )
}

@Composable
fun MainContent(
    isContextualMenu : Boolean,
    itemsForRemoveSize : Int = 0,
    taskList: List<Task>,
    addNewTaskOnAction: () -> Unit,
    updateTaskStatusOnAction: (Task) -> Unit,
    removeTasksOnAction: () -> Unit,
    updateContextualMenuOnAction : (Boolean) -> Unit,
    clearContextualMenuOnAction : () -> Unit,
    addNewItemForRmoveOnAction : (Task) -> Unit,
    itemForRemoveIsCheck : (Task) -> Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isContextualMenu && itemsForRemoveSize > 0)
                        Text(
                            text = stringResource(id = R.string.count_for_remove_selected, itemsForRemoveSize),
                            modifier = Modifier.testTag(ConstTestTAG.MainScreen.TopAppBarTitleTAG)
                        )
                    else
                        Text(
                            text = stringResource(id = R.string.app_name),
                            modifier = Modifier.testTag(ConstTestTAG.MainScreen.TopAppBarTitleTAG)
                        )
                },
                navigationIcon =
                if (isContextualMenu)
                    ({
                        IconButton(
                            onClick =  {
                                updateContextualMenuOnAction(false)
                                clearContextualMenuOnAction()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back_label)
                            )
                        }
                    })
                else
                    null,
                actions = {
                    if (isContextualMenu && itemsForRemoveSize > 0)
                        IconButton(onClick = {
                            removeTasksOnAction()
                            clearContextualMenuOnAction()
                            updateContextualMenuOnAction(false)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(id = R.string.delete_label)
                            )
                        }

                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        floatingActionButton = {
            if (isContextualMenu.not())
                AddTaskFab(onClick = addNewTaskOnAction)
        },
        modifier = Modifier.testTag(ConstTestTAG.MainScreen.TopAppBarTAG)
    ) {
        ListTaskContent(
            isMenuContextual = isContextualMenu,
            taskList = taskList,
            checkboxUpdateTaskStatus = updateTaskStatusOnAction,
            updateMenuContextualOnClick = {  updateContextualMenuOnAction(true) },
            addNewItemForRmoveOnAction = addNewItemForRmoveOnAction,
            itemForRemoveIsCheck = itemForRemoveIsCheck
        )
    }
}

@Composable
fun ListTaskContent(
    isMenuContextual: Boolean,
    taskList: List<Task>,
    checkboxUpdateTaskStatus: (Task) -> Unit,
    updateMenuContextualOnClick: () -> Unit,
    addNewItemForRmoveOnAction : (Task) -> Unit,
    itemForRemoveIsCheck : (Task) -> Boolean
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(ConstTestTAG.MainScreen.ListTaskContentTAG)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(taskList) { task ->
            TaskItemCard(
                isMenuContextual = isMenuContextual,
                task = task,
                radioButtonIsSelect = itemForRemoveIsCheck(task),
                checkBosUpdateTaskStatus = checkboxUpdateTaskStatus,
                itemContextualMenuOnClick = updateMenuContextualOnClick,
                radioButtonUpdateTaskStatus = {
                    addNewItemForRmoveOnAction(task)
                }
            )
        }
    }
}

@Composable
fun AddTaskFab(onClick: () -> Unit) {
    FloatingActionButton(shape = Shapes.small, onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Add,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = stringResource(id = R.string.add_task_label)
        )
    }
}

@Composable
fun TaskItemCard(
    isMenuContextual: Boolean,
    task: Task,
    radioButtonIsSelect: Boolean,
    checkBosUpdateTaskStatus: (Task) -> Unit,
    radioButtonUpdateTaskStatus: (Task) -> Unit,
    itemContextualMenuOnClick: () -> Unit
) {

    val checkedState = remember { mutableStateOf(task.isDone) }

    val constraints = ConstraintSet {

        val descriptionConst = createRefFor("description")
        val markerConst = createRefFor("marker")
        val guidelineH = createGuidelineFromAbsoluteRight(0.1f)

        constrain(descriptionConst) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(guidelineH, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(markerConst) {
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
            .clickable { itemContextualMenuOnClick() }
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
                textDecoration =
                if
                        (task.isDone) TextDecoration.LineThrough
                else
                    TextDecoration.None,
                modifier = Modifier.layoutId("description")
            )
            if (isMenuContextual)
                RadioButton(
                    selected = radioButtonIsSelect,
                    onClick = {
                        radioButtonUpdateTaskStatus(task)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueGray900
                    ),
                    modifier = Modifier.layoutId("marker")
                )
            else
                Checkbox(
                    checked = checkedState.value,
                    colors = CheckboxDefaults.colors(
                        checkedColor = BlueGray900
                    ),
                    onCheckedChange = {
                        checkBosUpdateTaskStatus(task)
                        checkedState.value = task.isDone
                    },
                    modifier = Modifier.layoutId("marker")
                )
        }
    }
}

@Preview
@Composable
fun MainScreenLightPreview() {

    val tasks: List<Task> = listOf(
        Task(description = "Lavar as roupas", isDone = true),
        Task(description = "Limpar a casa", isDone = true),
        Task(description = "Passar na feira comprar Banana, Laranja, Manga e Maracujá"),
        Task(description = "Consulta dentista 01/01/2022 às 15h:30m")
    )

    ToDoTheme(darkTheme = false) {
        MainContent(
            isContextualMenu = true,
            itemsForRemoveSize = 0,
            taskList = tasks,
            addNewTaskOnAction = {},
            updateTaskStatusOnAction = { task -> },
            removeTasksOnAction = {},
            updateContextualMenuOnAction = { newContextualMenuState -> },
            clearContextualMenuOnAction = {},
            addNewItemForRmoveOnAction = { newTask -> },
            itemForRemoveIsCheck = { task -> false}
        )
    }

}

@Preview
@Composable
fun MainScreenDarkPreview() {

    val tasks: List<Task> = listOf(
        Task(description = "Lavar as roupas", isDone = true),
        Task(description = "Limpar a casa", isDone = true),
        Task(description = "Passar na feira comprar Banana, Laranja, Manga e Maracujá"),
        Task(description = "Consulta dentista 01/01/2022 às 15h:30m")
    )

    ToDoTheme(darkTheme = true) {
        MainContent(
            isContextualMenu = false,
            itemsForRemoveSize = 0,
            taskList = tasks,
            addNewTaskOnAction = {},
            updateTaskStatusOnAction = { task -> },
            removeTasksOnAction = {},
            updateContextualMenuOnAction = { newContextualMenuState -> },
            clearContextualMenuOnAction = {},
            addNewItemForRmoveOnAction = { newTask -> },
            itemForRemoveIsCheck = { task -> false}
        )
    }

}