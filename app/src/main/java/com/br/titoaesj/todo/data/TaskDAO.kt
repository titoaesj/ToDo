package com.br.titoaesj.todo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task : Task) : Long

    @Query("SELECT * FROM tasks")
    fun getAllTasks() : Flow<List<Task>>

    @Update
    fun update(vararg tasks : Task) : Int

}