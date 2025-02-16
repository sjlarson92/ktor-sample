package com.example

import com.example.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        get("/tasks") {
            val tasks = TaskRepository.allTasks()
            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = tasks.tasksAsTable()
            )
        }

        get("/tasks/{priority}") {
            val priorityAsString = call.parameters["priority"]

            val priority = if (priorityAsString != null) {
                Priority.valueOf(priorityAsString)
            } else null

            if (priority == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val tasks = TaskRepository.getByPriority(priority)

            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = tasks.tasksAsTable()
            )
        }

        post("/tasks") {
            val requestBody = call.receiveParameters()

            val newTask = Task(
                name = requestBody["name"] ?: "",
                description = requestBody["description"] ?: "",
                priority = Priority.valueOf(requestBody["priority"] ?: "Low")
                )

            TaskRepository.addTask(newTask)

            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = "new Task created: $newTask"
            )
        }
    }


}
