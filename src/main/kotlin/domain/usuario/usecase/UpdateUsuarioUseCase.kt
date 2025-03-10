package domain.usuario.usecase

import domain.usuario.models.UpdateUsuario

import domain.usuario.repository.UsuarioInterface

class UpdateUsuarioUseCase (val repository : UsuarioInterface){
    var updateUsuario: UpdateUsuario? = null
    var dni: String? = null

    suspend operator fun invoke() : Boolean {
        return if (updateUsuario == null || dni == null) {
            false
        }else{
            return repository.updateUsuario(updateUsuario!!, dni!!)
        }

    }
}