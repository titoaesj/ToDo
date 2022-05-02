package com.br.titoaesj.todo.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.titoaesj.todo.data.Task
import com.br.titoaesj.todo.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository): ViewModel() {

    var tasks by mutableStateOf<List<Task>>(listOf())

    fun addTask(task: Task) {
        taskRepository.insertTask(task = task)
    }

    fun reload() {
        viewModelScope.launch {
            taskRepository.getAllTask().collect { value ->
                tasks = value
            }
        }
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task = task)
    }

    fun removeTasks(tasks: List<Task>) {
        taskRepository.removeTasks(tasks = tasks)
    }


    init {
        reload()
    }

}