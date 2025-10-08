package com.weg.gestao_escola.service;

import com.weg.gestao_escola.repository.AlunoDAO;
import com.weg.gestao_escola.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escola.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escola.mapper.AlunoMapper;
import com.weg.gestao_escola.model.Aluno;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AlunoService {

    private AlunoDAO repository;
    private AlunoMapper mapper;

    public AlunoService(AlunoDAO repository, AlunoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AlunoRespostaDTO> listarAlunos() throws SQLException {
        return repository.listarAlunos()
                .stream()
                .map(mapper::paraRespostaDTO)
                .toList();
    }

    public AlunoRespostaDTO buscarAlunoPorId(int id) throws SQLException{
        Aluno aluno = repository.buscarAlunoPorId(id);

        if(aluno == null){
            throw new RuntimeException("Aluno não existe!");
        }

        return mapper.paraRespostaDTO(aluno);
    }

    public AlunoRespostaDTO criar(AlunoRequisicaoDTO requisicaoDTO) throws SQLException{
        return mapper.paraRespostaDTO(repository.criar(mapper.paraEntidade(requisicaoDTO)));
    }

    public AlunoRespostaDTO atualizar(int id, AlunoRequisicaoDTO requisicaoDTO) throws SQLException{
        Aluno aluno = repository.buscarAlunoPorId(id);

        if(aluno == null){
            throw new RuntimeException("Aluno não existe!");
        }

        Aluno newAluno = mapper.paraUpdate(requisicaoDTO, aluno);
        repository.atualizar(id, newAluno);
        return mapper.paraRespostaDTO(newAluno);
    }

    public void excluir(int id) throws SQLException{
        Aluno aluno = repository.buscarAlunoPorId(id);

        if(aluno == null){
            throw new RuntimeException("Aluno não existe!");
        }

        repository.excluir(id);
    }
}
