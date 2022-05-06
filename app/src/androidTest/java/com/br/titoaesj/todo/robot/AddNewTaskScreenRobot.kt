package com.br.titoaesj.todo.robot

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.br.titoaesj.todo.data.ConstTestTAG

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 30 de março de 2022.
 */
internal class AddNewTaskScreenRobot(val composeContentTestRule: ComposeContentTestRule) {

    val addButtonField by lazy { composeContentTestRule.onNodeWithTag(ConstTestTAG.AddNewTaskScreen.AddButtonTAG) }
    val inputNewTaskField by lazy { composeContentTestRule.onNodeWithTag(ConstTestTAG.AddNewTaskScreen.TextFieldNewTaskTAG) }

    fun inputNewTask(note: String): SemanticsNodeInteraction =
        composeContentTestRule.insertTextIntoView(
            viewTAG = ConstTestTAG.AddNewTaskScreen.TextFieldNewTaskTAG,
            text = note
        )

    fun addButtonFiedlInititalState(): SemanticsNodeInteraction =
        addButtonField
            .assertIsDisplayed()
            .assertTextEquals("Adicionar")
            .assertContentDescriptionEquals("Adicionar tarefa")
            .assertIsEnabled()

    fun inputNewTaskFieldlInititalState(): SemanticsNodeInteraction =
        inputNewTaskField
            .assertIsDisplayed()
            .assertTextContains("Nova atividade...")
            .assertIsEnabled()


}