package br.com.wnascimento.tydo.data.config

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.wnascimento.tydo.data.task.TaskDao
import br.com.wnascimento.tydo.task.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "task.db")
                        .build()
            }
            return instance
        }

    }


    abstract fun taskDao(): TaskDao

}