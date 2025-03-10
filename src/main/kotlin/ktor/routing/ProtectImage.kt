package ktor.routing

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.ApplicationContext
import ktor.validateToken
import java.io.File

fun Route.imgRouting(){
    route("/images/{dni}/{image}"){
        authenticate("jwt-auth"){
            get() {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validado = call.validateToken(token!!)
                if (!validado) {
                    return@get
                }
                val dni = call.parameters["dni"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Necesitamos el idAviso")
                val nameImage = call.parameters["image"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Necesitamos la imagen")
                val path = ApplicationContext.context.environment.config.property("ktor.path.images").getString() + "/$dni"
                val img = File(path, nameImage)
                if (!img.exists()){
                    return@get call.respond(HttpStatusCode.BadRequest, "Imagen no encontrada")
                }
                call.respondFile(img)
            }
        }
    }
}