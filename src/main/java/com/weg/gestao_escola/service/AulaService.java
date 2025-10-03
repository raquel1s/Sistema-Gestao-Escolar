package com.weg.gestao_escola.service;

import com.weg.gestao_escola.dao.AulaDAO;
import com.weg.gestao_escola.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escola.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escola.mapper.AulaMapper;
import com.weg.gestao_escola.model.Aluno;
import com.weg.gestao_escola.model.Aula;
import com.weg.gestao_escola.model.AulaResposta;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AulaService {

    private AulaDAO repository;
    private AulaMapper mapper;

    public AulaService(AulaDAO repository, AulaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AulaRespostaDTO> listarAulas() throws SQLException {
        List<AulaResposta> aulaRespostas = new ArrayList<>();

        for(Aula aula : repository.listarAulas()){
            aulaRespostas.add(repository.buscarAulaPorIdResposta(aula.getId()));
        }

        return aulaRespostas.stream()
                .map(mapper::paraRespostaDTO)
                .toList();
    }

    public AulaRespostaDTO buscarAulaPorId(int id) throws SQLException{
        AulaResposta aula = repository.buscarAulaPorIdResposta(id);

        if(aula == null){
            throw new RuntimeException("Aula não existe!");
        }

        return mapper.paraRespostaDTO(aula);
    }

    public AulaRespostaDTO criar(AulaRequisicaoDTO requisicaoDTO) throws SQLException{
        Aula aula = repository.criar(mapper.paraEntidade(requisicaoDTO));
        return mapper.paraRespostaDTO(repository.buscarAulaPorIdResposta(aula.getId()));
    }

    public AulaRespostaDTO atualizar(int id, AulaRequisicaoDTO requisicaoDTO) throws SQLException{
        Aula aula = repository.buscarAulaPorId(id);

        if(aula == null){
            throw new RuntimeException("Aula não existe!");
        }

        Aula newAula = mapper.paraUpdate(requisicaoDTO, aula);
        repository.atualizar(id, newAula);
        return mapper.paraRespostaDTO(repository.buscarAulaPorIdResposta(id));
    }

    public void excluir(int id) throws SQLException{
        Aula aula = repository.buscarAulaPorId(id);

        if(aula == null){
            throw new RuntimeException("Aula não existe!");
        }

        repository.excluir(id);
    }
}
