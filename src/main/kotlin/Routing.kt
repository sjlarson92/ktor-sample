package com.example

import com.example.model.*
import io.ktor.http.*
import io.ktor.server.application.*
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
    }


}
