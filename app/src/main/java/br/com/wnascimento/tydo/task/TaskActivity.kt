package br.com.wnascimento.tydo.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.wnascimento.tydo.Injection
import br.com.wnascimento.tydo.R
import br.com.wnascimento.tydo.util.formatForCompletDateTime
import kotlinx.android.synthetic.main.task_activity.*
import java.util.*


class TaskActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskViewModelFactory: TaskViewModelFactory
    private lateinit var taskItemAdapter: TaskItemAdapter

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_activity)
        initViewModel()
        loadTasks()
        addSaveTaskListener()
        addDateTaskListener()
    }



    private fun initViewModel() {
        taskViewModelFactory = Injection.providerViewModelFactory(this)
        taskViewModel = ViewModelProviders.of(this, taskViewModelFactory).get(TaskViewModel::class.java)
    }

    private fun loadTasks() {
        listTasks.layoutManager = LinearLayoutManager(this)

        taskViewModel.getTasks().observe(this, Observer { tasks ->
            makeListTasks(tasks)
            addRemoveTaskListener()
        })
    }

    private fun makeListTasks(tasks: List<TaskRowAdapter>?) {
        taskItemAdapter = TaskItemAdapter(tasks!!.toMutableList())
        listTasks.adapter = taskItemAdapter
    }

    private fun addSaveTaskListener() {
        fabSave.setOnClickListener({
            taskViewModel.saveTask(taskTitle.text.toString(), calendar.timeInMillis)
            taskTitle.text.clear()
        })
    }


    fun addDateTaskListener(){

        buttonDate.setOnClickListener({
            val timeDialog = TimePickerDialog(this, { view,  hours,  minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hours)
                calendar.set(Calendar.MINUTE, minute)
                taskDate.visibility = View.VISIBLE
                taskDate.text = formatForCompletDateTime(calendar.timeInMillis)
            }, 10, 10, false)

            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                timeDialog.show()
            }, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) ,calendar.get(Calendar.YEAR)).show()
        })
    }

    private fun addRemoveTaskListener() {
        taskItemAdapter.onRemove = { task, position ->
            run {
                AlertDialog.Builder(this)
                        .setTitle(getString(R.string.title_warning))
                        .setMessage(getString(R.string.msg_remove_task))
                        .setPositiveButton(getString(R.string.action_yes), { _, _ ->
                            run {
                                taskItemAdapter.notifyTaskRemoved(position)
                                taskViewModel.removeTask(task)
                            }
                        })
                        .setNegativeButton(getString(R.string.action_no), null)
                        .show()

            }
        }
    }

}
