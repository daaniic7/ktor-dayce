package domain.avisos.usecase

import domain.avisos.infraestructure.Utils
import domain.avisos.repository.AvisoInterface

class DeleteAvisosUseCase(val repository: AvisoInterface) {
    var idAviso: Int? = null

    suspend operator fun invoke(): Boolean {
        return if (idAviso == null) {
            false
        } else {
            val aviso = repository.getAvisosById(idAviso!!)
            aviso?.let { a ->
                a.imagenUrl?.let { img ->
                    Utils.deleteImage(idAviso.toString(), img)
                    Utils.deleteDirectory(idAviso.toString())
                }
                return repository.deleteAvisos(idAviso!!)
            }
            false
        }
    }
}
