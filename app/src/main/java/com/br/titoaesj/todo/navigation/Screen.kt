package com.br.titoaesj.todo.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
sealed class Screen(
    val router: String,
    val TAG: String,
    val icon: ImageVector? = null,
    @StringRes val resourceId: Int? = null
) {

    object MainScreen : Screen(
        router = "mainscreen_router",
        TAG = "mainscreen_tag"
    )

    object AddNewTaskcreen : Screen(
        router = "addnewtaskscreen_router",
        TAG = "addnewtaskactivity_tag"
    )

    fun withArgs(vararg args: String): String {
        return buildString {
            append(router)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}