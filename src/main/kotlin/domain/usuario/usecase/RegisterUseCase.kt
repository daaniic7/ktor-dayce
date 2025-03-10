package domain.usuario.usecase

import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class RegisterUseCase(val repository: UsuarioInterface) {
    operator suspend fun invoke(usuario: UpdateUsuario): Usuario? {

        usuario.dni = usuario.dni!!
        usuario.password = usuario.password!!
        usuario.name = usuario.name ?: "Sin nombre"
        usuario.email = usuario.name ?: "ejemplo@gmail.com"
        usuario.token = usuario.token?: ""

        return if (repository.login(usuario.dni!!, usuario.password!!)!=null)
                    null
                else
                    repository.register(usuario)
    }
}