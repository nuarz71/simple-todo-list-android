package io.github.nuarz71.todolist.android.domain

import io.github.nuarz71.todolist.android.data.repository.ToDoRepository
import io.github.nuarz71.todolist.android.data.repository.parameter.AddEditTaskParameter
import io.github.nuarz71.todolist.android.domain.usecase.AddTaskUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class AddToDoUseCaseTest {
    private lateinit var SUT: AddTaskUseCase
    private lateinit var repositoy: ToDoRepository
    
    @Before
    fun setup() {
        repositoy = mockk(relaxed = true)
        SUT = AddTaskUseCase(repository = repositoy)
    }
    
    @Test
    fun `Add To Do Success`() = runTest {
        
        coEvery {
            repositoy.addOrEditTask(TEST_DATA)
        } returns Result.success(true)
        
        val result = SUT(
            title = TEST_DATA.title,
            dueDate = TEST_DATA.dueDate,
            description = TEST_DATA.description
        )
        
        coVerify(exactly = 1) {
            repositoy.addOrEditTask(TEST_DATA)
        }
        assertEquals(result.getOrNull(), true)
    }
    
    @Test
    fun `Add To Do Failed`() = runTest {
        
        coEvery {
            repositoy.addOrEditTask(TEST_DATA)
        } returns Result.success(false)
        
        val result = SUT(
            title = TEST_DATA.title,
            dueDate = TEST_DATA.dueDate,
            description = TEST_DATA.description
        )
        
        coVerify(exactly = 1) {
            repositoy.addOrEditTask(TEST_DATA)
        }
        assertEquals(result.getOrNull(), false)
    }
    
    @Test
    fun `Add To Do Got Exception`() = runTest {
        
        coEvery {
            repositoy.addOrEditTask(TEST_DATA)
        } returns Result.failure(TEST_EXCEPTION)
        
        val result = SUT(
            title = TEST_DATA.title,
            dueDate = TEST_DATA.dueDate,
            description = TEST_DATA.description
        )
        
        coVerify(exactly = 1) {
            repositoy.addOrEditTask(TEST_DATA)
        }
        assertEquals(result.exceptionOrNull(), TEST_EXCEPTION)
    }
    
    companion object {
        val TEST_DATA = AddEditTaskParameter(
            title = "Testing",
            dueDate = LocalDateTime.now(),
            description = null
        )
        
        val TEST_EXCEPTION = IllegalArgumentException("Testing")
    }
}