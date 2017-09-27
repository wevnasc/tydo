package br.com.wnascimento.tydo.data.task

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface TaskDao {

    @Insert
    fun save(task: Task)

    @Query("SELECT * FROM task")
    fun all() : LiveData<List<Task>>

    @Update
    fun update(task: Task)

    @Delete
    fun destroy(task: Task)

}