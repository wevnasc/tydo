package br.com.wnascimento.tydo.task

import br.com.wnascimento.tydo.util.formatForRelativeTime

class TaskRowAdapter(val task: Task = Task(), val section: String = "", var isSection: Boolean) {

    companion object {

        fun buildListTaskBySectionDate(tasks: List<Task>): List<TaskRowAdapter> {
            val list = tasks.sortedWith(compareBy({ it.date }))
            return makeListAdapter(list)
        }

        private fun makeListAdapter(list: List<Task>): ArrayList<TaskRowAdapter> {
            var currentDate = ""
            var index = 0
            val listRowAdapter = ArrayList<TaskRowAdapter>()
            while (index < list.size) {
                val task = list[index]
                val dateFormatted = formatForRelativeTime(task.date)
                if (dateFormatted != currentDate) {
                    currentDate = dateFormatted
                    listRowAdapter.add(TaskRowAdapter(section = dateFormatted, isSection = true))
                } else {
                    listRowAdapter.add(TaskRowAdapter(task = task, isSection = false))
                    index++
                }
            }
            return listRowAdapter
        }
    }

}