package com.felipecoronado.smarttasks.ui.utils

import com.felipecoronado.smarttasks.ui.models.TaskModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

fun filterTasksByDate(tasks: List<TaskModel>, date: LocalDate): List<TaskModel> {
    return tasks.filter { LocalDate.parse(it.targetDate).isEqual(date) }
}


// Function to parse due date to "Sep-01-2024" format
fun formatDueDate(dueDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
    val date = LocalDate.parse(dueDate, inputFormatter)
    return date.format(outputFormatter)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}

// Function to get days left in "16" format
fun getDaysLeft(dueDate: String, targetDate: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val due = LocalDate.parse(dueDate, formatter)
    val target = LocalDate.parse(targetDate, formatter)
    return ChronoUnit.DAYS.between(target, due).toString()
}


//Iterate through all dates up until latest date,
//stop and show day if it contains tasks, if its out of bounds or if its today.
fun findNextDateWithTasks(
    startDate: LocalDate,
    latestDate: LocalDate,
    tasks: List<TaskModel>
): LocalDate {
    val today = LocalDate.now()
    var date = startDate
    while (filterTasksByDate(tasks, date).isEmpty()) {
        if (date.isEqual(today)) {
            return today
        }
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

//Iterate through all dates up until earliest date,
//stop and show day if it contains tasks, if its out of bounds or if its today.
fun findPreviousDateWithTasks(
    startDate: LocalDate,
    earliestDate: LocalDate,
    tasks: List<TaskModel>
): LocalDate {
    val today = LocalDate.now()
    var date = startDate
    while (filterTasksByDate(tasks, date).isEmpty()) {
        if (date.isEqual(today)) {
            return today
        }
        date = date.minusDays(1)
        if (date.isBefore(earliestDate)) {
            return startDate
        }

    }
    return date
}

