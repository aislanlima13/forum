package com.alura.forum.service

import com.alura.forum.dto.AtualizacaoTopicoForm
import com.alura.forum.dto.NovoTopicoForm
import com.alura.forum.dto.TopicoView
import com.alura.forum.exception.NotFoundExcepetion
import com.alura.forum.mapper.TopicoFormMapper
import com.alura.forum.mapper.TopicoViewMapper
import com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private val repository: TopicoRepository,
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico não encontrado") {
    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map { t ->
            topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
                .orElseThrow{NotFoundExcepetion(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView{
        val topico = topicoFormMapper.map(dto)
        repository.save(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
                .orElseThrow{NotFoundExcepetion(notFoundMessage)}

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem

        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}
