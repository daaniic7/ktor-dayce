package domain.avisos.repository

import domain.avisos.models.Aviso
import domain.avisos.models.UpdateAviso

interface AvisoInterface {
    suspend fun getAllAvisos () : List <Aviso>

    suspend fun getAvisosByName ( name : String) : Aviso?

    suspend fun getAvisosById (idAviso: Int) : Aviso?

    suspend fun postAvisos(Aviso: Aviso) : Boolean

    suspend fun updateAvisos(Aviso: UpdateAviso, idAviso: Int) : Boolean

    suspend fun deleteAvisos(idAviso : Int) : Boolean
}