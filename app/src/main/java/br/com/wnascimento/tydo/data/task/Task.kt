package br.com.wnascimento.tydo.data.task

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity(tableName = "task")
data class Task(var title: String = "", var date: Long = Date().time) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

}