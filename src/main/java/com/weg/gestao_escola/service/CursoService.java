package com.weg.gestao_escola.service;

import com.weg.gestao_escola.dao.CursoDAO;
import com.weg.gestao_escola.dto.curso.CursoRequisicaoDTO;
import com.weg.gestao_escola.dto.curso.CursoRespostaDTO;
import com.weg.gestao_escola.mapper.CursoMapper;
import com.weg.gestao_escola.model.Curso;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    private CursoDAO repository;
    private CursoMapper mapper;

    public CursoService(CursoDAO repository, CursoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CursoRespostaDTO> listarCursos() throws SQLException {
        List<CursoRespostaDTO> cursos = new ArrayList<>();

        for(Curso curso : repository.listarCursos()){
            cursos.add(
                    mapper.paraRespostaDTO
                            (curso, repository.listarNomeProfessores(curso.getId())));
        }

        return cursos;
    }

    public CursoRespostaDTO buscarCursoPorId(int id) throws SQLException{
        Curso curso = repository.buscarCursoPorId(id);

        if(curso == null){
            throw new RuntimeException("Curso não existe!");
        }

        return mapper.paraRespostaDTO(curso, repository.listarNomeProfessores(curso.getId()));
    }

    public CursoRespostaDTO criar(CursoRequisicaoDTO requisicaoDTO) throws SQLException{
        List<String> professores = new ArrayList<>();

        for(int idProf : requisicaoDTO.listaProfessorIds()){
            professores.add(repository.listarNomeProfessoresPeloId(idProf));
        }

        return mapper.paraRespostaDTO(repository.criar(mapper.paraEntidade(requisicaoDTO)), professores);
    }

    public CursoRespostaDTO atualizar(int id, CursoRequisicaoDTO requisicaoDTO) throws SQLException{
        Curso curso = repository.buscarCursoPorId(id);

        if(curso == null){
            throw new RuntimeException("Professor não existe!");
        }

        List<String> professores = new ArrayList<>();

        for(int idProf : requisicaoDTO.listaProfessorIds()){
            professores.add(repository.listarNomeProfessoresPeloId(idProf));
        }

        Curso newCurso = mapper.paraUpdate(requisicaoDTO, curso);
        repository.atualizar(id, newCurso);
        return mapper.paraRespostaDTO(newCurso, professores);
    }

    public void excluir(int id) throws SQLException{
        Curso curso = repository.buscarCursoPorId(id);

        if(curso == null){
            throw new RuntimeException("Curso não existe!");
        }

        repository.excluir(id);
    }

}
