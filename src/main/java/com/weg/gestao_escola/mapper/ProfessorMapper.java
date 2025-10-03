package com.weg.gestao_escola.mapper;

import com.weg.gestao_escola.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRespostaDTO;
import com.weg.gestao_escola.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(ProfessorRequisicaoDTO requisicaoDTO){
        return new Professor(requisicaoDTO.nome(),
                requisicaoDTO.email(),
                requisicaoDTO.disciplina());
    }

    public ProfessorRespostaDTO paraRespostaDTO(Professor professor){
        return new ProfessorRespostaDTO(professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina());
    }

    public Professor paraUpdate(ProfessorRequisicaoDTO requisicaoDTO, Professor professor){
        if(!requisicaoDTO.nome().equals(professor.getNome()) && requisicaoDTO.nome() != null){
            professor.setNome(requisicaoDTO.nome());
        }

        if(!requisicaoDTO.email().equals(professor.getEmail()) && requisicaoDTO.email() != null){
            professor.setEmail(requisicaoDTO.email());
        }

        if(!requisicaoDTO.disciplina().equals(professor.getDisciplina()) && requisicaoDTO.disciplina() != null){
            professor.setDisciplina(requisicaoDTO.disciplina());
        }

        return professor;
    }
}
