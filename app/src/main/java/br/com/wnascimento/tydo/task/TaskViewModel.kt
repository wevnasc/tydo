package br.com.wnascimento.tydo.task

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import br.com.wnascimento.tydo.data.task.Task
import br.com.wnascimento.tydo.data.task.TaskDao
import java.util.*

@SuppressLint("StaticFieldLeak")
class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    private var tasks: LiveData<List<Task>> = MutableLiveData<List<Task>>()

    fun saveTask(title: String) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                taskDao.save(Task(title = title))
                return null
            }
        }.execute()

    }

    fun getTasks() : LiveData<List<Task>> {
        return Transformations.map(taskDao.all(), {
                list -> list.sortedWith(compareBy({it.date}, {it.title})).reversed()
            })
    }


}