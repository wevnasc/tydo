package br.com.wnascimento.tydo.task

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "task")
class Task(var title: String = "", var date: Long = Date().time) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}