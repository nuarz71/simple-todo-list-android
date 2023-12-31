package io.github.nuarz71.todolist.android.domain

import io.github.nuarz71.todolist.android.data.entity.TaskEntity
import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.domain.usecase.ListTaskUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class ListToDoUseCaseTest {
    private lateinit var SUT: ListTaskUseCase
    private lateinit var repositoy: ToDoRepository
    
    @Before
    fun setup() {
        repositoy = mockk(relaxed = true)
        SUT = ListTaskUseCase(repository = repositoy)
    }
    
    @Test
    fun `List To Do Success with empty`() = runTest {
        
        every {
            repositoy.taskList()
        } returns TEST_EMPTY_FLOW_ENTITY
        
        val result = SUT()
        
        verify(exactly = 1) {
            repositoy.taskList()
        }
        assertEquals(result.first().isEmpty(), true)
    }
    
    @Test
    fun `List To Do Success with not empty`() = runTest {
        
        every {
            repositoy.taskList()
        } returns TEST_FLOW_ENTITY
        
        val result = SUT()
        
        verify(exactly = 1) {
            repositoy.taskList()
        }
        assertEquals(result.first().first().id, TEST_ENTITY.id)
    }
    
    companion object {
        val TEST_ENTITY = TaskEntity(
            id = 1,
            title = "Testing",
            dueDate = LocalDateTime.now(),
            description = null
        )
        
        val TEST_EMPTY_FLOW_ENTITY = flowOf(emptyList<TaskEntity>())
        val TEST_FLOW_ENTITY = flowOf(listOf(TEST_ENTITY))
        
        val TEST_EXCEPTION = IllegalArgumentException("Testing")
    }
}