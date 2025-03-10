package ktor.routing

import domain.avisos.models.Aviso
import domain.avisos.models.UpdateAviso
import domain.avisos.usecase.AvisosProviderUseCase
import domain.usuario.usecase.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import ktor.validateToken
import java.util.*

fun Route.avisosRouting() {
    route("/avisos") {
        authenticate("jwt-auth") {
            get {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validado = token?.let { call.validateToken(it) } ?: false
                if (!validado) {
                    call.respond(HttpStatusCode.Unauthorized, "El token ha fallado")
                    return@get
                }
                val listAvisos = AvisosProviderUseCase.getAllAvisos()
                call.respond(listAvisos.invoke())
            }

            delete("{idAviso}") {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validado = token?.let { call.validateToken(it) } ?: false
                if (!validado) {
                    call.respond(HttpStatusCode.Unauthorized, "El token ha fallado")
                    return@delete
                }
                val idAviso = call.parameters["idAviso"]?.toIntOrNull()
                if (idAviso == null) {
                    call.respond(HttpStatusCode.BadRequest, "ID inválido")
                    return@delete
                }
                ProviderUseCase.logger.warn("Se borrará el aviso con id: $idAviso")
                val respuesta = AvisosProviderUseCase.deleteAvisos(idAviso)
                if (!respuesta) {
                    call.respond(HttpStatusCode.NotFound, "Ese aviso no existe")
                } else {
                    call.respondText("Aviso eliminado")
                }
            }

            get("{idAviso}") {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validado = token?.let { call.validateToken(it) } ?: false
                if (!validado) {
                    call.respond(HttpStatusCode.Unauthorized, "El token ha fallado")
                    return@get
                }
                val idAviso = call.parameters["idAviso"]?.toIntOrNull()
                if (idAviso == null) {
                    call.respond(HttpStatusCode.BadRequest, "No has mandado id válido")
                    return@get
                }
                val aviso = AvisosProviderUseCase.getAvisosById(idAviso)
                if (aviso == null) {
                    call.respond(HttpStatusCode.NotFound, "No hay aviso con esa id")
                    return@get
                }
                call.respond(aviso)
            }

            post {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validado = token?.let { call.validateToken(it) } ?: false
                if (!validado) {
                    call.respond(HttpStatusCode.Unauthorized, "El token ha fallado")
                    return@post
                }
                try {
                    val requestBody = call.receiveText()
                    println("JSON recibido: $requestBody")
                    val aviso = Json.decodeFromString<Aviso>(requestBody)
                    val respuesta = AvisosProviderUseCase.postAvisos(aviso)
                    if (respuesta == null) {
                        call.respond(HttpStatusCode.Conflict, "No se ha podido insertar")
                    } else {
                        call.respond(HttpStatusCode.Created, respuesta)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    call.respond(HttpStatusCode.BadRequest, "Error: ${e.localizedMessage}")
                }
            }

            patch("{idAviso}") {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
                val validado = token?.let { call.validateToken(it) } ?: false
                if (!validado) {
                    call.respond(HttpStatusCode.Unauthorized, "El token ha fallado")
                    return@patch
                }
                try {
                    val idAviso = call.parameters["idAviso"]?.toIntOrNull()
                    if (idAviso == null) {
                        call.respond(HttpStatusCode.BadRequest, "Falta el id válido")
                        return@patch
                    }
                    val updateAviso = call.receive<UpdateAviso>()
                    val res = AvisosProviderUseCase.updateAvisos(idAviso, updateAviso)
                    if (res == null) {
                        call.respond(HttpStatusCode.Conflict, "Algo ha fallado al modificar")
                    } else {
                        call.respond(HttpStatusCode.OK, "Se ha actualizado")
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Algo ha fallado y ha saltado el catch: ${e.localizedMessage}")
                }
            }
        }
    }
}