package domain.avisos.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAviso(
    var idAviso: Int,  // Se cambió `id` por `idAviso` para ser consistente
    var nombre: String,
    var direccion: String,
    var fecha: String,
    var descripcion: String,
    var imagenUrl: String  // Se cambió `imagen` por `imagenUrl`
)
