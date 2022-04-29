package com.br.titoaesj.todo.ui.screen

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.br.titoaesj.todo.robot.AddNewTaskScreenRobot
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 30 de março de 2022.
 */
class AddNewTaskScreenTest {

    @get:Rule
    val composeTesteRule: ComposeContentTestRule = createComposeRule()

    private var addNewTaskRobot: AddNewTaskScreenRobot? = null

    @Before
    fun setUp() {
        composeTesteRule.setContent {
            AddNewTaskContent(
                onClickAddTask = {},
                onBackPressed = {}
            )
        }

        addNewTaskRobot = AddNewTaskScreenRobot(composeContentTestRule = composeTesteRule)

    }

    @Test
    fun inputNewTaskInitialState() {
        addNewTaskRobot?.inputNewTaskFieldlInititalState()
    }

    @Test
    fun addButonInitialState() {
        addNewTaskRobot?.addButtonFiedlInititalState()
    }

    @Test
    fun inputNewTask() {
        addNewTaskRobot?.inputNewTask("Olá mundo")?.assertTextEquals("Olá mundo")
    }
}