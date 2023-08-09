package com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NovoTopicoForm (
        @field:NotEmpty @field:Size(min = 5, max = 100, message = "Título deve conter entre 5 e 100 caracteres")
        val titulo: String,

        @field:NotEmpty(message = "Mensagem não preenchida")
        val mensagem: String,

        @field:NotNull
        val idCurso: Long,

        @field:NotNull
        val idAutor: Long
)