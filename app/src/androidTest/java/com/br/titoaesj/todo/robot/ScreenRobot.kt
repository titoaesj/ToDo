package com.br.titoaesj.todo.robot

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 30 de março de 2022.
 */

fun ComposeTestRule.insertTextIntoView(viewTAG: String, text: String) : SemanticsNodeInteraction {
    val node = this.onNodeWithTag(viewTAG)
        node.performTextInput(text = text)
    Espresso.closeSoftKeyboard()
    return node
}