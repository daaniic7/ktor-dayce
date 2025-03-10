package domain.avisos.usecase

import domain.avisos.models.Aviso
import domain.avisos.repository.AvisoInterface
import ktor.ApplicationContext

class GetAvisosByNameUseCase(val repository: AvisoInterface) {
    var name: String? = null

    suspend operator fun invoke(): Aviso? {
        return if (name.isNullOrBlank()) {
            null
        } else {
            val aviso = repository.getAvisosByName(name!!)
            aviso?.let {
                if (!it.imagenUrl.isNullOrBlank()) {
                    val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                    val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                    it.imagenUrl = "$local/$relativePath/${it.idAviso}/${it.imagenUrl}"
                }
            }
            aviso
        }
    }
}
