package com.weg.gestao_escola.service;

import com.weg.gestao_escola.dao.ProfessorDAO;
import com.weg.gestao_escola.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRespostaDTO;
import com.weg.gestao_escola.mapper.ProfessorMapper;
import com.weg.gestao_escola.model.Professor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProfessorService {

    private ProfessorDAO repository;
    private ProfessorMapper mapper;

    public ProfessorService(ProfessorDAO repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProfessorRespostaDTO> listarProfessor() throws SQLException {
        return repository.listarProfessores()
                .stream()
                .map(mapper::paraRespostaDTO)
                .toList();
    }

    public ProfessorRespostaDTO buscarProfessorPorId(int id) throws SQLException{
        Professor professor = repository.buscarProfessorPorId(id);

        if(professor == null){
            throw new RuntimeException("Professor não existe!");
        }

        return mapper.paraRespostaDTO(repository.buscarProfessorPorId(id));
    }

    public ProfessorRespostaDTO criar(ProfessorRequisicaoDTO requisicaoDTO) throws SQLException{
        return mapper.paraRespostaDTO(repository.criar(mapper.paraEntidade(requisicaoDTO)));
    }

    public ProfessorRespostaDTO atualizar(int id, ProfessorRequisicaoDTO requisicaoDTO) throws SQLException{
        Professor professor = repository.buscarProfessorPorId(id);

        if(professor == null){
            throw new RuntimeException("Professor não existe!");
        }

        Professor newProfessor = mapper.paraUpdate(requisicaoDTO, professor);
        repository.atualizar(id, newProfessor);
        return mapper.paraRespostaDTO(newProfessor);
    }

    public void excluir(int id) throws SQLException{
        Professor professor = repository.buscarProfessorPorId(id);

        if(professor == null){
            throw new RuntimeException("Professor não existe!");
        }

        repository.excluir(id);
    }
}
