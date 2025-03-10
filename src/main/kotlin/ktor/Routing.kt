package ktor

import ktor.routing.avisosRouting
import ktor.routing.authRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        authRouting()
        avisosRouting()
        staticResources("/static", "static")
        staticFiles("/images", File("Uploads/images"))
        staticFiles("/files", File("Uploads/files"))
    }
}