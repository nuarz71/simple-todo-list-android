package io.github.nuarz71.todolist.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskEntity>)
    
    @Query("SELECT * FROM todo WHERE id=:id")
    suspend fun getTaskById(id: Long): TaskEntity?
    
    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllToDo(): Flow<List<TaskEntity>>
    
    @Query("DELETE FROM todo WHERE id=:id")
    fun deleteTaskById(id: Long): Long
    
    @Query("DELETE FROM todo")
    fun deleteAllTask()
}