package com.br.titoaesj.todo.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.br.titoaesj.todo.ui.screen.AddNewTaskScreen
import com.br.titoaesj.todo.ui.screen.MainScreen
import com.br.titoaesj.todo.ui.screen.TaskViewModel

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
@Composable
fun DefaultNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.router
    ) {

        composable(route = Screen.MainScreen.router) {
            val taskViewModel = hiltViewModel<TaskViewModel>()
            MainScreen(viewModel = taskViewModel, navigation = navController)
        }

        composable(route = Screen.AddNewTaskcreen.router) {
            val taskViewModel = hiltViewModel<TaskViewModel>()
            AddNewTaskScreen(viewModel = taskViewModel, navigation = navController)
        }

    }

}