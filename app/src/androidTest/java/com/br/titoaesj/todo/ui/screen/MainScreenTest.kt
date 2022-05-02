package com.br.titoaesj.todo.ui.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import com.br.titoaesj.todo.data.ConstTestTAG
import com.br.titoaesj.todo.data.Task
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 28 de março de 2022.
 */
class MainScreenTest {

    @get:Rule
    val composeTesteRule: ComposeContentTestRule = createComposeRule()

    private val mockTasks: List<Task> = listOf(
        Task(description = "Lavar as roupas", isDone = true),
        Task(description = "Limpar a casa", isDone = true),
        Task(description = "Passar na feira comprar Banana, Laranja, Manga e Maracujá"),
        Task(description = "Consulta dentista 01/01/2022 às 15h:30m")
    )

    @Before
    fun setUp() {
        composeTesteRule.setContent {
            MainContent(
                taskList = mockTasks,
                isMenuContextual = false,
                enterMenuContextualOnClick = {},
                exitMenuContextualOnClick = {},
                addNewTaskOnAction = {},
                updateTaskStatusOnAction = { }
            )
        }
    }

    @Test
    fun whenTopAppBarExists() {
        composeTesteRule.onNodeWithTag(ConstTestTAG.MainScreen.TopAppBarTAG).assertIsDisplayed()
    }

    @Test
    fun whenTopAppBarTitleWithText() {
        composeTesteRule.onNodeWithTag(ConstTestTAG.MainScreen.TopAppBarTitleTAG)
            .assertTextEquals("ToDo")
    }

    @Test
    fun whenFirstTaskItemCardIsMarked() {
        composeTesteRule.onNodeWithText(mockTasks.first().description)
            .onParent()
            .onChildren()
            .onLast()
            .assertIsOn()
    }

    @Test
    fun whenListTaskContentCountItens() {
        composeTesteRule.onNodeWithTag(ConstTestTAG.MainScreen.ListTaskContentTAG)
            .onChildren()
            .assertCountEquals(4)
    }

    @Test
    fun whenLastTaskItemCardIsUnmarked() {
        composeTesteRule.onNodeWithText(mockTasks.last().description)
            .onParent()
            .onChildren()
            .onLast()
            .assertIsOff()
    }

    @Test
    fun whenAddTaskButtonExists() {
        composeTesteRule.onNodeWithContentDescription("adicionar tarefa").assertIsDisplayed()
    }
}