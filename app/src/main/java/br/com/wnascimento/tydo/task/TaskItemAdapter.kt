package br.com.wnascimento.tydo.task

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wnascimento.tydo.R
import br.com.wnascimento.tydo.util.formatForHours
import kotlinx.android.synthetic.main.task_item.view.*
import kotlinx.android.synthetic.main.task_section.view.*

class TaskItemAdapter(private var taskList: MutableList<TaskRowAdapter>) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    companion object {
        val ITEM = 0
        val SECTION = 1
    }

    lateinit var onRemove: (Task, Int) -> Unit

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTask(taskRowAdapter: TaskRowAdapter, position: Int, listener: (Task, Int) -> Unit) {
            with(itemView) {
                if (itemViewType == ITEM) {
                    taskTitle.text = taskRowAdapter.task.title
                    buttonDate.text = formatForHours(taskRowAdapter.task.date)
                    setOnLongClickListener {
                        listener(taskRowAdapter.task, position)
                        return@setOnLongClickListener false
                    }
                } else {
                    sectionTitle.text = taskRowAdapter.section
                }
            }

        }


    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) = holder!!.bindTask(taskList[position], position, onRemove)

    override fun getItemCount(): Int = taskList.size

    override fun getItemViewType(position: Int): Int {
        if (taskList[position].isSection) return SECTION else return ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View
        if (viewType == ITEM) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.task_item, parent, false)
        } else {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.task_section, parent, false)
        }
        return ViewHolder(view);
    }

    fun notifyTaskRemoved(position: Int) {
        taskList.removeAt(position)
        notifyItemRemoved(position)
    }

}