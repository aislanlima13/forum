package com.alura.forum.service

import com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(var usuarios: List<Usuario>) {
    init {
        val usuario = Usuario(
                id = 1,
                nome = "Aislan",
                email = "aislan.martins99@gmail.com"
        )

        usuarios = listOf(usuario)
    }

    fun buscarPorId(id: Long): Usuario = usuarios.stream().filter { c ->
        c.id == id
    }.findFirst().get()
}
