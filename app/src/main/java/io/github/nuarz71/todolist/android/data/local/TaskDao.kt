package io.github.nuarz71.todolist.android.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

abstract class TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(todo: TaskEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(todos: List<TaskEntity>)
    
    @Query("SELECT * FROM todo WHERE id=:id")
    abstract suspend fun getToDo(id: Long): TaskEntity?
    
    @Query("SELECT * FROM todo ORDER BY id DESC")
    abstract fun getAllToDo(): Flow<List<TaskEntity>>
    
    @Query("DELETE FROM todo WHERE id=:id")
    abstract fun deleteToDo(id: Long): Long
}