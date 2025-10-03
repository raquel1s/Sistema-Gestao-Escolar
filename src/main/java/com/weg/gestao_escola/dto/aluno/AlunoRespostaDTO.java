package com.weg.gestao_escola.dto.aluno;

import java.time.LocalDate;

public record AlunoRespostaDTO(
        int id,
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
