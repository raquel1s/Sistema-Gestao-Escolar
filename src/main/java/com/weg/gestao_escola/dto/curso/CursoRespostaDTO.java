package com.weg.gestao_escola.dto.curso;

import java.util.List;

public record CursoRespostaDTO (
        int id,
        String nome,
        String codigo,
        List<String> listaProfessores
){
}
