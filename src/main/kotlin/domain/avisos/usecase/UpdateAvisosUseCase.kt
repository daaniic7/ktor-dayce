package domain.avisos.usecase

import domain.avisos.infraestructure.Utils
import domain.avisos.models.Aviso
import domain.avisos.models.UpdateAviso
import domain.avisos.repository.AvisoInterface

class UpdateAvisosUseCase(val repository: AvisoInterface) {
    var updateAviso: UpdateAviso? = null
    var idAviso: Int? = null

    suspend operator fun invoke(): Aviso? {
        return if (updateAviso == null || idAviso == null) {
            null
        } else {
            try {
                updateAviso?.imagenUrl?.let { newImg ->
                    val aviso = repository.getAvisosById(idAviso!!)
                    aviso?.imagenUrl?.let { oldImg ->
                        Utils.deleteImage(updateAviso!!.idAviso.toString(), oldImg)
                    }
                    val newImageUrl = Utils.createBase64ToImg(newImg, idAviso!!.toString())
                    updateAviso!!.imagenUrl = newImageUrl
                }
                return repository.updateAvisos(updateAviso!!, idAviso!!)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
