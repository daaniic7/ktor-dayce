package domain.avisos.usecase

import domain.avisos.models.Aviso
import domain.avisos.repository.AvisoInterface
import ktor.ApplicationContext

class GetAllAvisosUseCase(val repository: AvisoInterface) {
    suspend operator fun invoke(): List<Aviso> {
        val listAvisos = repository.getAllAvisos()
        return listAvisos.map { aviso ->
            if (!aviso.imagenUrl.isNullOrBlank()) {
                val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                aviso.imagenUrl = "$local/$relativePath/${aviso.idAviso}/${aviso.imagenUrl}"
            }
            aviso
        }
    }
}
