package domain.avisos.models

import kotlinx.serialization.Serializable

@Serializable
data class Aviso(
    val idAviso: Int,
    val nombre: String,
    val direccion: String,
    val fecha: String,
    val descripcion: String,
    var imagenUrl: String
)
