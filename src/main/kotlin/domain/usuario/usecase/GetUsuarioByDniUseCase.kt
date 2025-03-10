package domain.usuario.usecase

import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetUsuarioByDniUseCase (val repository : UsuarioInterface) {
    var dni : String? = null


    suspend operator fun invoke() : Usuario? {
        return if (dni?.isNullOrBlank() == true)
                null
            else{
                repository.getUsuarioByDni(dni!!)
        }
    }
}