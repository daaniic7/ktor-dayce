package domain.avisos.usecase

import domain.avisos.infraestructure.Utils
import domain.avisos.models.Aviso
import domain.avisos.repository.AvisoInterface
import ktor.ApplicationContext

class PostAvisosUseCase(val repository: AvisoInterface) {
    var aviso: Aviso? = null

    suspend operator fun invoke(): Aviso? {
        val existingAviso = repository.getAvisosById(aviso!!.idAviso)
        if (existingAviso != null) {
            return null
        }

        val isCreateDir = Utils.createDir(aviso!!.idAviso.toString())
        if (isCreateDir) {
            val img = aviso!!.imagenUrl
            if (!img.isNullOrBlank()) {
                aviso!!.imagenUrl = Utils.createBase64ToImg(img, aviso!!.idAviso.toString()).toString()
            }
        } else {
            throw IllegalStateException("No se pudo crear el directorio del aviso. Puede que ya exista")
        }

        val newAviso = repository.postAvisos(aviso!!)
        newAviso?.let {
            if (it.imagenUrl.isNotBlank()) {
                val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                it.imagenUrl = "$local/$relativePath/${it.idAviso}/${it.imagenUrl}"
            }
        }
        return newAviso
    }
}
