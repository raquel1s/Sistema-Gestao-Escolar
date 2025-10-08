package com.weg.gestao_escola.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record TurmaRequisicaoDTO (
        @NotBlank(message = "O nome é obrigatório!")
        String nome,
        @NotNull(message = "O id do curso é obrigatório!")
        @Positive(message = "O id precisa ser maior que zero!")
        int cursoId,
        @NotNull(message = "O id do professor é obrigatório!")
        @Positive(message = "O id precisa ser maior que zero!")
        int professorId,
        List<Integer> listaAlunoIds
){
}
