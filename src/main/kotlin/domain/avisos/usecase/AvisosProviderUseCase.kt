package domain.avisos.usecase

import domain.avisos.models.Aviso
import domain.avisos.models.UpdateAviso
import data.avisos.persistence.repository.PersistenceAvisosRepository
import domain.usuario.usecase.ProviderUseCase.logger

object AvisosProviderUseCase {
    val repository = PersistenceAvisosRepository()

    val getAllAvisosUseCase = GetAllAvisosUseCase(repository)
    val getAvisosByNameUseCase = GetAvisosByNameUseCase(repository)
    val getAvisosByIdUseCase = GetAvisosByIdUseCase(repository)
    val postAvisosUseCase = PostAvisosUseCase(repository)
    val updateAvisosUseCase = UpdateAvisosUseCase(repository)
    val deleteAvisosUseCase = DeleteAvisosUseCase(repository)

    suspend fun getAllAvisos() = getAllAvisosUseCase()

    suspend fun getAvisosByName(name: String): Aviso? {
        if (name.isBlank()) {
            logger.warn("El nombre del aviso está vacío")
            return null
        }
        getAvisosByNameUseCase.name = name
        return getAvisosByNameUseCase()
    }

    suspend fun getAvisosById(idAviso: Int): Aviso? {
        getAvisosByIdUseCase.idAviso = idAviso
        return getAvisosByIdUseCase()
    }

    suspend fun postAvisos(aviso: Aviso): Aviso? {
        postAvisosUseCase.aviso = aviso
        return postAvisosUseCase() // Antes devolvía un Boolean, ahora devuelve Aviso?
    }

    suspend fun updateAvisos(idAviso: Int, updateAviso: UpdateAviso?): Aviso? {
        updateAvisosUseCase.idAviso = idAviso
        updateAvisosUseCase.updateAviso = updateAviso
        return updateAvisosUseCase()
    }

    suspend fun deleteAvisos(idAviso: Int): Boolean {
        deleteAvisosUseCase.idAviso = idAviso
        return deleteAvisosUseCase()
    }
}
