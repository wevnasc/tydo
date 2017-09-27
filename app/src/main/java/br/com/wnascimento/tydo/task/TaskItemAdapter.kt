package br.com.wnascimento.tydo.task

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wnascimento.tydo.R
import br.com.wnascimento.tydo.data.task.Task
import kotlinx.android.synthetic.main.task_item.view.*

class TaskItemAdapter(private val taskList : List<Task>) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTask(task: Task) {
            with(task) {
                itemView.taskTitle.text = task.title
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) = holder!!.bindTask(taskList[position])

    override fun getItemCount(): Int = taskList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(v);
    }



}