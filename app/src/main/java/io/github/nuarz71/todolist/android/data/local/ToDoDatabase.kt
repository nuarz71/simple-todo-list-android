package io.github.nuarz71.todolist.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.nuarz71.todolist.android.data.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
    
    abstract val todoDao: TaskDao
    
    companion object {
        fun create(context: Context): ToDoDatabase {
            return Room.databaseBuilder(context, ToDoDatabase::class.java, "todolist.db")
                .build()
        }
    }
}