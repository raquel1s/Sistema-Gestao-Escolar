package com.weg.gestao_escola.dto.aula;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AulaRespostaDTO(
        int id,
        String nomeTurma,
        LocalDateTime dataHora,
        String assunto
) {
}
