package com.weg.gestao_escola.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequisicaoDTO(
        @NotBlank(message = "O nome é obrigatório!")
        String nome,
        @Email(message = "Email inválido!")
        @NotBlank(message = "O email é obrigatório!")
        String email,
        @NotBlank(message = "A disciplina é obrigatória!")
        String disciplina
) {
}
