package domain.usuario.mapping

import data.usuario.persistence.models.UsuarioDAO
import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario

fun Usuario.toUpdateUsuario(): UpdateUsuario {
    return UpdateUsuario(
        dni = dni,
        name = name,
        email = email,
        password = password,
        token = token
    )
}

fun UpdateUsuario.toUsuario() : Usuario {
    return Usuario(
        dni = dni!!,
        name = name!!,
        email = email!!,
        password = password!!,
        token = token!!
    )
}

fun UsuarioDAO.toUsuario() : Usuario {
    val usuario = Usuario(
        dni = dni,
        name = name,
        email = email,
        password = password,
        token = token ?: "",
    )
    return usuario
}