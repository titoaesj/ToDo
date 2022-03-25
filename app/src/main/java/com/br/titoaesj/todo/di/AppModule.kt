package com.br.titoaesj.todo.di

import android.content.Context
import androidx.room.Room
import com.br.titoaesj.todo.ToDoApplication
import com.br.titoaesj.todo.data.SnapshotsDatabase
import com.br.titoaesj.todo.data.TaskDAO
import com.br.titoaesj.todo.data.TaskRepository
import com.br.titoaesj.todo.ui.screen.TaskViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase( @ApplicationContext app : Context)
            = Room.databaseBuilder(app, SnapshotsDatabase::class.java, ToDoApplication.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideTaskDao(db : SnapshotsDatabase) = db.taskDAO()

    @Provides
    fun provideTaskRepository(taskDAO: TaskDAO) = TaskRepository(taskDAO = taskDAO)

}