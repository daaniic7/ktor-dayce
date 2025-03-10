package data.avisos.persistence.repository

import data.usuario.persistence.models.suspendTransaction
import domain.avisos.mapping.toAvisos
import domain.avisos.models.Aviso
import domain.avisos.models.UpdateAviso
import domain.avisos.repository.AvisoInterface
import data.avisos.persistence.models.AvisoDao
import data.avisos.persistence.models.AvisoTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update

class
PersistenceAvisosRepository : AvisoInterface {

    override suspend fun getAllAvisos(): List<Aviso> {
        return suspendTransaction {
            AvisoDao.all().map { it.toAvisos() }
        }
    }

    override suspend fun getAvisosByName(name: String): Aviso? {
        return suspendTransaction {
            AvisoDao.find { AvisoTable.name eq name }
                .limit(1)
                .map { it.toAvisos() }
                .firstOrNull()
        }
    }

    override suspend fun getAvisosById(idAviso: Int): Aviso? {
        return suspendTransaction {
            AvisoDao.find { AvisoTable.id eq idAviso }
                .limit(1)
                .map { it.toAvisos() }
                .firstOrNull()
        }
    }

    override suspend fun postAvisos(aviso: Aviso): Boolean {  // <-- Cambiado a postAvisos
        return suspendTransaction {
            val existeAviso = AvisoDao.find { AvisoTable.id eq aviso.idAviso }
                .limit(1)
                .map { it.toAvisos() }
                .firstOrNull()

            if (existeAviso == null) {
                AvisoDao.new {
                    this.name = aviso.nombre
                    this.description = aviso.descripcion
                    this.image = aviso.imagenUrl
                    this.direction = aviso.direccion  // <-- Añadido direction
                }
                true
            } else {
                false
            }
        }
    }

    override suspend fun updateAvisos(updateAviso: UpdateAviso, idAviso: Int): Boolean {
        return suspendTransaction {
            val rowsUpdated = AvisoTable.update({ AvisoTable.id eq idAviso }) {
                updateAviso.nombre?.let { name -> it[AvisoTable.name] = name }
                updateAviso.descripcion?.let { description -> it[AvisoTable.description] = description }
                updateAviso.imagen?.let { image -> it[AvisoTable.image] = image }
                updateAviso.direccion?.let { direction -> it[AvisoTable.direction] = direction }
            }
            rowsUpdated > 0
        }
    }


    override suspend fun deleteAvisos(idAviso: Int): Boolean {
        return suspendTransaction {
            val rowsDeleted = AvisoTable.deleteWhere { id eq idAviso }
            rowsDeleted > 0
        }
    }
}
