package com.felipecoronado.smarttasks.ui.utils

import com.felipecoronado.smarttasks.ui.models.TaskModel
import java.time.LocalDate

fun filterTasksByDate(tasks: List<TaskModel>, date: LocalDate): List<TaskModel> {
    return tasks.filter { LocalDate.parse(it.targetDate).isEqual(date) }
}

fun findNextDateWithTasks(
    startDate: LocalDate,
    latestDate: LocalDate,
    tasks: List<TaskModel>
): LocalDate {
    val today = LocalDate.now()
    var date = startDate
    while (filterTasksByDate(tasks, date).isEmpty()) {
        date = date.plusDays(1)
        if (date.isAfter(latestDate)) {
            return startDate
        }
        if (date.isEqual(today)) {
            return today
        }
    }
    return date
}

fun findPreviousDateWithTasks(
    startDate: LocalDate,
    earliestDate: LocalDate,
    tasks: List<TaskModel>
): LocalDate {
    val today = LocalDate.now()
    var date = startDate
    while (filterTasksByDate(tasks, date).isEmpty()) {
        date = date.minusDays(1)
        if (date.isBefore(earliestDate)) {
            return startDate
        }
        if (date.isEqual(today)) {
            return today
        }
    }
    return date
}

