package com.br.titoaesj.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
@Entity(
    tableName = "tasks"
)
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "description")
    val description : String,

    @ColumnInfo(name = "created_at")
    val createdAt: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "is_done")
    var isDone : Boolean = false

)