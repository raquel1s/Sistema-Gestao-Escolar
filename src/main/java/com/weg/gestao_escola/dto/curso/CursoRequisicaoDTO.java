package com.weg.gestao_escola.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CursoRequisicaoDTO(
        @NotBlank(message = "O nome é obrigatório!")
        String nome,
        @NotBlank(message = "O código do curso é obrigatório!")
        String codigo,
        List<Integer> listaProfessorIds
) {
}
