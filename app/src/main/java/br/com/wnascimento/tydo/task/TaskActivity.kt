package br.com.wnascimento.tydo.task

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.wnascimento.tydo.Injection
import br.com.wnascimento.tydo.R
import kotlinx.android.synthetic.main.task_activity.*

class TaskActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var taskViewModel : TaskViewModel
    private lateinit var taskViewModelFactory: TaskViewModelFactory
    private lateinit var taskItemAdapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_activity)
        initViewModel()
        loadTasks()
        saveTask()
    }

    private fun initViewModel() {
        taskViewModelFactory = Injection.providerViewModelFactory(this)
        taskViewModel = ViewModelProviders.of(this, taskViewModelFactory).get(TaskViewModel::class.java)
    }

    private fun loadTasks() {
        listTasks.layoutManager = LinearLayoutManager(this)

        taskViewModel.getTasks().observe(this, Observer { tasks ->
            taskItemAdapter = TaskItemAdapter(tasks!!)
            listTasks.adapter = taskItemAdapter
        })
    }

    private fun saveTask() {
        fabSave.setOnClickListener({ taskViewModel.saveTask(taskTitle.text.toString()) })
    }



}
