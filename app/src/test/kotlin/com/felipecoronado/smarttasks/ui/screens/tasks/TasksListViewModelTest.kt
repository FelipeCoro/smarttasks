package com.felipecoronado.smarttasks.ui.screens.tasks

import com.felipecoronado.smarttasks.ui.screens.util.MainCoroutineExtension
import com.felipecoronado.smarttasks.ui.screens.util.RepositoryFake
import com.felipecoronado.smarttasks.ui.utils.DateUtils
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class TasksListViewModelTest {

    private lateinit var viewModel: TasksListViewModel
    private lateinit var repository: RepositoryFake
    private val dateUtils: DateUtils = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        repository = RepositoryFake()
        viewModel = TasksListViewModel(repository, dateUtils)
    }

    @Test
    fun `should fetch tasks list correctly`() = runTest {
        viewModel.getAllTasks()
        advanceUntilIdle()
        val taskList = viewModel.uiState.value.tasks
        assertEquals(repository.taskList, taskList)
    }
}