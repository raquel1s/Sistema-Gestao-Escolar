package com.weg.gestao_escola.dto.curso;

import java.util.List;

public record CursoRequisicaoDTO(
        String nome,
        String codigo,
        List<Integer> listaProfessorIds
) {
}
