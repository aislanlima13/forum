package com.alura.forum.service

import com.alura.forum.dto.AtualizacaoTopicoForm
import com.alura.forum.dto.NovoTopicoForm
import com.alura.forum.dto.TopicoView
import com.alura.forum.exception.NotFoundExcepetion
import com.alura.forum.mapper.TopicoFormMapper
import com.alura.forum.mapper.TopicoViewMapper
import com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico> = ArrayList(),
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico não encontrado") {
    fun listar(): List<TopicoView> {
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundExcepetion(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView{
        val topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == form.id
        }.findFirst().orElseThrow{NotFoundExcepetion(notFoundMessage)}

        val topicoAtualizado = Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                curso = topico.curso,
                autor = topico.autor,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
        )

        topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundExcepetion(notFoundMessage)}

        topicos = topicos.minus(topico)
    }
}
