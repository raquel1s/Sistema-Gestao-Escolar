package com.weg.gestao_escola.mapper;

import com.weg.gestao_escola.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escola.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escola.model.Turma;
import com.weg.gestao_escola.model.TurmaResposta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurmaMapper {

    public Turma paraEntidade(TurmaRequisicaoDTO requisicaoDTO){
        return new Turma(requisicaoDTO.nome(), requisicaoDTO.cursoId(), requisicaoDTO.professorId());
    }

    public TurmaRespostaDTO paraRespostaDTO(TurmaResposta turma, List<String> alunos){
        return new TurmaRespostaDTO(turma.getId(), turma.getNome(), turma.getNomeCurso(), turma.getNomeProfessor(), alunos);
    }

    public Turma paraUpdate(TurmaRequisicaoDTO requisicaoDTO, Turma turma){
        if(!requisicaoDTO.nome().equals(turma.getNome()) && !requisicaoDTO.nome().isEmpty()){
            turma.setNome(requisicaoDTO.nome());
        }

        if(requisicaoDTO.cursoId() != turma.getCursoId()){
            turma.setCursoId(requisicaoDTO.cursoId());
        }

        if(requisicaoDTO.professorId() != turma.getProfessorId()){
            turma.setProfessorId(requisicaoDTO.professorId());
        }

        return turma;
    }
}
