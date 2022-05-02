package com.br.titoaesj.todo.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
class TaskRepository @Inject constructor (private val taskDAO: TaskDAO) {

    fun insertTask(task : Task) : Long = taskDAO.insert(task)

    fun getAllTask() : Flow<List<Task>> = taskDAO.getAllTasks()

    fun updateTask(task : Task) : Int = taskDAO.update(task)

    fun removeTasks(tasks : List<Task>) : Int = taskDAO.remove(tasks)

}