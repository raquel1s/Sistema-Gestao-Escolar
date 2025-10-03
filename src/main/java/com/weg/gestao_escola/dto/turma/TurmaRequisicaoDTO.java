package com.weg.gestao_escola.dto.turma;

import java.util.List;

public record TurmaRequisicaoDTO (
        String nome,
        int cursoId,
        int professorId,
        List<Integer> listaAlunoIds
){
}
