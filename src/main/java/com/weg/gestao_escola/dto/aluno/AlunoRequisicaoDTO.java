package com.weg.gestao_escola.dto.aluno;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AlunoRequisicaoDTO(
        @NotBlank(message = "O nome é obrigatório!")
        String nome,
        @Email(message = "Email inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,
        @NotBlank(message = "A matrícula é obrigatória!")
        String matricula,
        @NotNull(message = "A data é obrigatória!")
        @Past(message = "A data inválida!")
        LocalDate dataNascimento
) {
}
