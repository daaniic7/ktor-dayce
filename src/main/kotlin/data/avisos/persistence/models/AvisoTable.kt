package data.avisos.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object AvisoTable : IntIdTable("Aviso") {
    val name = varchar("name", 100)
    val description = varchar("description", 255)
    val image = varchar("image", 1000)
    val direction = varchar("direction", 255)
}
