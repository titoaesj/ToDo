package com.br.titoaesj.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */

@Database(
    version = SnapshotsDatabase.VERSION,
    entities = [ Task::class ],
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class SnapshotsDatabase : RoomDatabase() {

    companion object {
        const val VERSION : Int = 1
    }

    abstract fun taskDAO(): TaskDAO

}