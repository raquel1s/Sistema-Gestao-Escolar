package com.weg.gestao_escola.dto.aula;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AulaRequisicaoDTO (
        int turmaId,
        LocalDateTime dataHora,
        String assunto
){
}
