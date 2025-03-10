package domain.avisos.mapping

import data.avisos.persistence.models.AvisoDao
import domain.avisos.models.Aviso
import domain.avisos.models.UpdateAviso

fun Aviso.toUpdateAviso(): UpdateAviso {
    return UpdateAviso(
        id = idAviso.toInt(),  // Convierte si es String, o ajusta el tipo de idAviso a Int en Aviso
        nombre = nombre,
        descripcion = descripcion,
        imagen = imagenUrl,
        direccion = TODO(),
        fecha = TODO()
    )
}

fun UpdateAviso.toAviso(): Aviso {
    return Aviso(
        idAviso = id!!.toInt(),  // Asegúrate de que coincida el tipo
        nombre = nombre!!,
        descripcion = descripcion!!,
        direccion = "",  // si falta en UpdateAviso, rellena con valor vacío
        fecha = "",      // lo mismo aquí
        imagenUrl = imagen!!
    )
}

fun AvisoDao.toAvisos(): Aviso {
    return Aviso(
        idAviso = id.value.toInt(),  // Aquí id es EntityID<Int>, por lo que usamos id.value
        nombre = name,
        descripcion = description,
        direccion = direction ?: "",
        fecha = "",  // rellénalo si no tienes la columna en la tabla
        imagenUrl = image ?: ""
    )
}
