package com.felipecoronado.smarttasks.ui.screens.tasksdetails

import com.felipecoronado.smarttasks.ui.models.ResolvedStatus
import com.felipecoronado.smarttasks.ui.screens.util.MainCoroutineExtension
import com.felipecoronado.smarttasks.ui.screens.util.RepositoryFake
import com.felipecoronado.smarttasks.ui.screens.util.task1
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class TasksDetailsViewModelTest {

    private lateinit var viewModel: TaskDetailsViewModel
    private lateinit var repository: RepositoryFake

    @BeforeEach
    fun setUp() {
        repository = RepositoryFake()
        viewModel = TaskDetailsViewModel(repository)
    }

    @Test
    fun `should fetch task details correctly`() = runTest {
        viewModel.getTaskDetails("1")
        advanceUntilIdle()
        val task = viewModel.uiState.value.task
        assertEquals(task1, task)
    }

    @Test
    fun `should update task status correctly`() = runTest {
        viewModel.updateTaskStatus(true, "3", false, "")
        advanceUntilIdle()
        val updatedTask = viewModel.uiState.value.task
        assertEquals(updatedTask.resolvedStatus, ResolvedStatus.RESOLVED)
    }
}