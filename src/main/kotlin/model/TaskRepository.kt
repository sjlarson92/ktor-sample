package com.example.model

object TaskRepository {
    private val tasks = mutableListOf(
        Task("cleaning", "Clean house", Priority.Low),
        Task("cleaning", "Laundry", Priority.Medium),
        Task("shopping", "groceries", Priority.High),
        Task("cleaning", "take out trash", Priority.Vital)
    )

    fun allTasks(): List<Task> {
        return tasks
    }

    fun getByPriority(priority: Priority): List<Task> {
        return tasks.filter { it.priority == priority }
    }

    fun getByName(name:String): Task? {
        return tasks.find { it.name.equals(name, ignoreCase = true) }
    }

    fun addTask(task: Task): Task {
        if (getByName(task.name) != null) {
            throw IllegalStateException("Task name already exists!!!")
        }
        tasks.add(task)

        return task
    }

}