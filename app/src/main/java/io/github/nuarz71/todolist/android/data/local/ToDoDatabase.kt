package io.github.nuarz71.todolist.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class ToDoDatabase : RoomDatabase() {
    
    abstract val taskDao: TaskDao
    
    companion object {
        fun create(context: Context): ToDoDatabase {
            return Room.databaseBuilder(context, ToDoDatabase::class.java, "todolist.db")
                .build()
        }
    }
}

object LocalDateTimeConverter {
    @TypeConverter
    fun timestampToLocalDateTime(value: String?): LocalDateTime? {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }
    
    @TypeConverter
    fun localDateTimeToTimestamp(dateTime: LocalDateTime?): String? {
        val zonedDateTime = dateTime?.atZone(ZoneOffset.UTC)
        return zonedDateTime?.toString()
    }
}