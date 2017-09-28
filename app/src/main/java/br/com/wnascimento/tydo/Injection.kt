package br.com.wnascimento.tydo

import android.content.Context
import br.com.wnascimento.tydo.data.config.AppDatabase
import br.com.wnascimento.tydo.data.task.TaskDao
import br.com.wnascimento.tydo.task.TaskViewModelFactory


object Injection {

    fun providerTaskDataSource(context: Context): TaskDao {
        val database = AppDatabase.getDatabase(context)
        return database!!.taskDao()
    }

    fun providerViewModelFactory(context: Context): TaskViewModelFactory {
        val dataSource = providerTaskDataSource(context)
        return TaskViewModelFactory(dataSource)
    }

}