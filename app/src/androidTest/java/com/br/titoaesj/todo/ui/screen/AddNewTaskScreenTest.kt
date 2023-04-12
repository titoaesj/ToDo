package com.br.titoaesj.todo.ui.screen

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

    @Before
    fun setUp() {
        composeTesteRule.setContent {
            AddNewTaskContent(
                onClickAddTask = {},
                onBackPressed = {}
            )
        }

        AddNewTaskScreenRobot.setup(composeTesteRule)

    }

    @Test
    fun inputNewTaskInitialState() {
        AddNewTaskScreenRobot.apply {
            arrange {
                inputNewTaskFieldArrange()
            }
            assert {
                inputNewTaskFieldAssert()
            }
        }
    }

    @Test
    fun addButonInitialState() {
        AddNewTaskScreenRobot.apply {
            arrange {
                addButtonFieldArrange()
            }
            assert {
                addButtonFieldInitStateAssert()
            }
        }
    }

    @Test
    fun inputNewTask() {
        AddNewTaskScreenRobot.apply {
            arrange {
                inputNewTaskFieldArrange()
            }
            act {
                addButtonFieldInitStateAct()
            }
            assert {
                inputNewTaskFieldNewNoteAssert()
            }
        }
    }
}