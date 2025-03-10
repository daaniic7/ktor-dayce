package data.avisos.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AvisoDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AvisoDao>(AvisoTable)

    var name by AvisoTable.name
    var description by AvisoTable.description
    var direction by AvisoTable.direction
    var image by AvisoTable.image
}
