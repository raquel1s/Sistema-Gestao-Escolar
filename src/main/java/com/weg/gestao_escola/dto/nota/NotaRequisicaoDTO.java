package com.weg.gestao_escola.dto.nota;

import jakarta.validation.constraints.*;

public record NotaRequisicaoDTO(
        @NotNull(message = "O id do aluno é obrigatório!")
        @Positive(message = "O id precisa ser maior que zero!")
        int alunoId,
        @NotNull(message = "O id da aula é obrigatório!")
        @Positive(message = "O id precisa ser maior que zero!")
        int aulaId,
        @NotNull(message = "O nota é obrigatório!")
        @Max(value = 10, message = "A nota não pode ser maior que 10!")
        @PositiveOrZero(message = "A nota não pode ser menor que 0!")
        double valor
) {
}
