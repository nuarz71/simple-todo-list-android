package io.github.nuarz71.todolist.android.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.nuarz71.todolist.android.data.repository.DefaultToDoRepository
import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    
    @Singleton
    @Provides
    fun provideRepository(): ToDoRepository = DefaultToDoRepository()
}