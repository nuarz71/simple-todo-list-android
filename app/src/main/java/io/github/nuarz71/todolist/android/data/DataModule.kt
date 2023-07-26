package io.github.nuarz71.todolist.android.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.nuarz71.todolist.android.data.local.ToDoDatabase
import io.github.nuarz71.todolist.android.data.repository.DefaultToDoRepository
import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    
    @Singleton
    @Provides
    fun provideToDoDatabase(@ApplicationContext context: Context) = ToDoDatabase.create(context)
    
    @Singleton
    @Provides
    fun provideRepository(db: ToDoDatabase): ToDoRepository = DefaultToDoRepository(db)
}