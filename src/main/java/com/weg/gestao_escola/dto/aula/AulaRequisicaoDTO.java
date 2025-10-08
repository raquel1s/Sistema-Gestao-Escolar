package com.weg.gestao_escola.dto.aula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AulaRequisicaoDTO (
        @NotNull(message = "O id da turma é obrigatório!")
        @Positive(message = "O id precisa ser maior que zero!")
        int turmaId,
        @NotNull(message = "A data é obrigatória!")
        LocalDateTime dataHora,
        @NotBlank(message = "O assunto é obrigatório!")
        String assunto
){
}
