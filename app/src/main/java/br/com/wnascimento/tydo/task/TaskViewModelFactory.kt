package br.com.wnascimento.tydo.task

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.wnascimento.tydo.data.task.TaskDao


class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        if (modelClass != null) {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                return TaskViewModel(taskDao) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}