package com.alura.forum.service

import com.alura.forum.model.Usuario
import com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private val repository: UsuarioRepository) {
    fun buscarPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }
}
