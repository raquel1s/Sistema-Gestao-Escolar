package com.weg.gestao_escola.mapper;

import com.weg.gestao_escola.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escola.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escola.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(AlunoRequisicaoDTO requisicaoDTO){
        return new Aluno(requisicaoDTO.nome(),
                requisicaoDTO.email(),
                requisicaoDTO.matricula(),
                requisicaoDTO.dataNascimento());
    }

    public AlunoRespostaDTO paraRespostaDTO(Aluno aluno){
        return new AlunoRespostaDTO(aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getDataNascimento());
    }

    public Aluno paraUpdate(AlunoRequisicaoDTO requisicaoDTO, Aluno aluno){
        if(!requisicaoDTO.nome().equals(aluno.getNome()) && requisicaoDTO.nome() != null){
            aluno.setNome(requisicaoDTO.nome());
        }

        if(!requisicaoDTO.email().equals(aluno.getEmail()) && requisicaoDTO.email() != null){
            aluno.setEmail(requisicaoDTO.email());
        }

        if(!requisicaoDTO.matricula().equals(aluno.getMatricula()) && requisicaoDTO.matricula() != null){
            aluno.setMatricula(requisicaoDTO.matricula());
        }

        if(requisicaoDTO.dataNascimento() != aluno.getDataNascimento() && requisicaoDTO.dataNascimento() != null){
            aluno.setDataNascimento(requisicaoDTO.dataNascimento());
        }

        return aluno;
    }
}
