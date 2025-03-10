package domain.avisos.usecase

import domain.avisos.models.Aviso
import domain.avisos.repository.AvisoInterface
import ktor.ApplicationContext

class GetAvisosByIdUseCase(private val repository: AvisoInterface) {
    var idAviso: Int? = null

    suspend operator fun invoke(): Aviso? {
        val avisoId = idAviso ?: return null
        val aviso = repository.getAvisosById(avisoId)
        aviso?.let {
            if (!aviso.imagenUrl.isNullOrBlank()) {
                val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                aviso.imagenUrl = "$local/$relativePath/$avisoId/${aviso.imagenUrl}"
            }
        }
        return aviso
    }
}

