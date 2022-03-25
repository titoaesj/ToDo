package com.br.titoaesj.todo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */

@HiltAndroidApp
class ToDoApplication : Application() {

    companion object {
        const val DATABASE_NAME = "todo-database"
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(applicationContext)

    }

}