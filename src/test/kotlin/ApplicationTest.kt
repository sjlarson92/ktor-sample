package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun getByPriority() = testApplication {
        /*
           In each of these tests a new instance of Ktor is created.
           This is running inside a test environment,
           instead of a web server like Netty.
           The module written for you by the Project Generator is loaded,
           which in turn invokes the routing function.
            */
        application {
            module()
        }

        val response = client.get("/tasks/Low")
        val body = response.bodyAsText()

        assertEquals(HttpStatusCode.OK, response.status)
        assertContains(body, "Clean house")
    }
}