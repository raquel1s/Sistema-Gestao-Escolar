package com.weg.gestao_escola.mapper;

import com.weg.gestao_escola.dto.curso.CursoRequisicaoDTO;
import com.weg.gestao_escola.dto.curso.CursoRespostaDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRespostaDTO;
import com.weg.gestao_escola.model.Curso;
import com.weg.gestao_escola.model.Professor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public Curso paraEntidade(CursoRequisicaoDTO requisicaoDTO){
        return new Curso(requisicaoDTO.nome(),
                requisicaoDTO.codigo());
    }

    public CursoRespostaDTO paraRespostaDTO(Curso curso, List<String> professores){
        return new CursoRespostaDTO(curso.getId(),
        curso.getNome(), curso.getCodigo(), professores);
    }

    public Curso paraUpdate(CursoRequisicaoDTO requisicaoDTO, Curso curso){
        if(!requisicaoDTO.nome().equals(curso.getNome()) && requisicaoDTO.nome() != null){
            curso.setNome(requisicaoDTO.nome());
        }

        if(!requisicaoDTO.codigo().equals(curso.getCodigo()) && requisicaoDTO.codigo() != null){
            curso.setCodigo(requisicaoDTO.codigo());
        }

        return curso;
    }
}
