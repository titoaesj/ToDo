package com.br.titoaesj.todo.robot

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.br.titoaesj.todo.data.ConstTestTAG

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 30 de março de 2022.
 */
object AddNewTaskScreenRobot {

    private lateinit var composeContentTestRule: ComposeContentTestRule
    private lateinit var addButtonField: SemanticsNodeInteraction
    private lateinit var inputNewTaskField: SemanticsNodeInteraction

    private const val noteDummy: String = "Olá mundo"

    /**
     * Config
     */
    fun setup(composeContentTestRule: ComposeContentTestRule) {
        this.composeContentTestRule = composeContentTestRule
    }

    /**
     * Arrange function to prepare the test.
     *
     * @param func The [AddNewTaskScreenArrange] instance to apply all options requested
     *
     */
    infix fun arrange(func: AddNewTaskScreenArrange.() -> Unit) =
        AddNewTaskScreenArrange().apply(func)

    /**
     * Act function to prepare the test.
     *
     * @param func The [AddNewTaskScreenAct] instance to apply all options requested
     *
     */
    infix fun act(func: AddNewTaskScreenAct.() -> Unit) = AddNewTaskScreenAct().apply(func)

    /**
     * Assert function to prepare the test.
     *
     * @param func The [AddNewTaskScreenAssert] instance to perform all assertions requested
     *
     */
    infix fun assert(func: AddNewTaskScreenAssert.() -> Unit) =
        AddNewTaskScreenAssert().apply(func)

    class AddNewTaskScreenArrange {
        fun addButtonFieldArrange() {
            addButtonField =
                composeContentTestRule.onNodeWithTag(ConstTestTAG.AddNewTaskScreen.AddButtonTAG)
        }

        fun inputNewTaskFieldArrange() {
            inputNewTaskField =
                composeContentTestRule.onNodeWithTag(ConstTestTAG.AddNewTaskScreen.TextFieldNewTaskTAG)
        }
    }

    class AddNewTaskScreenAct {

        fun addButtonFieldInitStateAct() {
            composeContentTestRule.insertTextIntoView(
                viewTAG = ConstTestTAG.AddNewTaskScreen.TextFieldNewTaskTAG,
                text = noteDummy
            )
        }
    }

    class AddNewTaskScreenAssert {
        fun addButtonFieldInitStateAssert() {
            addButtonField
                .assertIsDisplayed()
                .assertTextEquals("Adicionar")
                .assertContentDescriptionEquals("Adicionar tarefa")
                .assertIsEnabled()
        }

        fun inputNewTaskFieldAssert() {
            inputNewTaskField
                .assertIsDisplayed()
                .assertTextContains("Nova atividade...")
                .assertIsEnabled()
        }

        fun inputNewTaskFieldNewNoteAssert() {
            inputNewTaskField
                .assertIsDisplayed()
                .assertTextContains(noteDummy)
                .assertIsEnabled()
        }
    }


}