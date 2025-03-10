package domain.usuario.usecase

import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetAllUsuarioUseCase (val repository : UsuarioInterface){

    suspend operator fun invoke(): List<Usuario> = repository.getAllUsuario()
}