package com.felipecoronado.smarttasks.ui.screens.tasks

import com.felipecoronado.smarttasks.ui.utils.DateUtils
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class TasksListViewModelTest {

    private lateinit var viewModel: TasksListViewModel
    private lateinit var repository: RepositoryFake
    private val dateUtils: DateUtils = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        repository = RepositoryFake()
        viewModel = TasksListViewModel(repository, dateUtils)

    }
    @AfterEach
    fun tearDown(){
        Dispatchers.shutdown()
    }


    @Test
    fun `taskListViewModel should fetch tasks list correctly`() = runTest{
        viewModel.getAllTasks()
        advanceUntilIdle()
        val taskList = viewModel.uiState.value.tasks
        assertEquals(repository.taskList, taskList)
    }
}