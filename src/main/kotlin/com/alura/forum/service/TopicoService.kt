package com.alura.forum.service

import com.alura.forum.model.Curso
import com.alura.forum.model.Topico
import com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: List<Topico>) {

    init {
        val topico = Topico(
                id = 1,
                titulo = "Duvida kotlin",
                mensagem = "Variaveis no kotlin",
                curso = Curso(
                        id = 1,
                        nome = "Kotlin",
                        categoria = "Programação"
                ),
                autor = Usuario(
                        id = 1,
                        nome = "Aislan",
                        email = "aislan.martins99@gmail.com"
                )
        )

        val topico2 = Topico(
                id = 2,
                titulo = "Duvida kotlin",
                mensagem = "Variaveis no kotlin",
                curso = Curso(
                        id = 2,
                        nome = "Kotlin",
                        categoria = "Programação"
                ),
                autor = Usuario(
                        id = 1,
                        nome = "Aislan",
                        email = "aislan.martins99@gmail.com"
                )
        )

        topicos = listOf(topico, topico2)
    }

    fun listar(): List<Topico> = topicos

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
    }
}
