package com.weg.gestao_escola.service;

import com.weg.gestao_escola.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escola.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escola.mapper.TurmaMapper;
import com.weg.gestao_escola.model.Turma;
import com.weg.gestao_escola.model.TurmaResposta;
import com.weg.gestao_escola.repository.TurmaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    private TurmaDAO repository;
    private TurmaMapper mapper;

    public TurmaService(TurmaDAO repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TurmaRespostaDTO> listarTurmas() throws SQLException {
        List<TurmaRespostaDTO> cursos = new ArrayList<>();

        for(TurmaResposta turma : repository.listarCursos()){
            cursos.add(
                    mapper.paraRespostaDTO
                            (turma, repository.listarNomeAlunos(turma.getId())));
        }

        return cursos;
    }

    public TurmaRespostaDTO buscarTurmaPorId(int id) throws SQLException{
        TurmaResposta turma = repository.buscarTurmaPorId(id);

        if(turma == null){
            throw new RuntimeException("Curso não existe!");
        }

        return mapper.paraRespostaDTO(turma, repository.listarNomeAlunos(turma.getId()));
    }

    public TurmaRespostaDTO criar(TurmaRequisicaoDTO requisicaoDTO) throws SQLException {
        List<String> nomeAlunos = repository.listaAlunoNome(requisicaoDTO.listaAlunoIds());

        Turma turma = repository.criar(mapper.paraEntidade(requisicaoDTO));

        for (int id : requisicaoDTO.listaAlunoIds()) {
            repository.inserirTurmaAluno(turma.getId(), id);
        }

        return mapper.paraRespostaDTO(repository.buscarTurmaPorId(turma.getId()), nomeAlunos);
    }

    public TurmaRespostaDTO atualizar(int id, TurmaRequisicaoDTO requisicaoDTO) throws SQLException{
        Turma turma = repository.buscarTurmaPorId(id);

        if(turma == null){
            throw new RuntimeException("Turma não existe!");
        }

        List<String> alunos = repository.listaAlunoNome(requisicaoDTO.listaAlunoIds());

        Turma newTurma = mapper.paraUpdate(requisicaoDTO, turma);
        repository.atualizar(id, newTurma);
        return mapper.paraRespostaDTO(repository.buscarTurmaPorId(turma.getId()), alunos);
    }

    public void excluir(int id) throws SQLException{
        TurmaResposta turma = repository.buscarTurmaPorId(id);

        if(turma == null){
            throw new RuntimeException("Turma não existe!");
        }

        repository.excluir(id);
    }
}
