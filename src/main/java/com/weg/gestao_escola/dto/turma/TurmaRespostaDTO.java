package com.weg.gestao_escola.dto.turma;

import java.util.List;

public record TurmaRespostaDTO (
        int id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<String> listaAlunos
){
}
