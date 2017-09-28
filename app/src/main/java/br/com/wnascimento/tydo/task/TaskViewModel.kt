package br.com.wnascimento.tydo.task

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import br.com.wnascimento.tydo.data.task.TaskDao

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    fun saveTask(title: String, date: Long) {
        SaveTask(title = title, date = date).execute()
    }

    fun getTasks(): LiveData<List<TaskRowAdapter>> {
        return Transformations.map(taskDao.all(), TaskRowAdapter.Companion::buildListTaskBySectionDate)
    }

    fun removeTask(task: Task) {
        RemoveTask(task).execute()
    }

    private inner class SaveTask(val title: String, val date: Long) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            taskDao.save(Task(title = title, date = date))
            return null
        }
    }

    private inner class RemoveTask(val task: Task) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            taskDao.destroy(task)
            return null
        }
    }


}