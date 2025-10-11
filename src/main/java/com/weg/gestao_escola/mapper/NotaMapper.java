package com.weg.gestao_escola.mapper;

import com.weg.gestao_escola.dto.nota.NotaRequisicaoDTO;
import com.weg.gestao_escola.dto.nota.NotaRespostaDTO;
import com.weg.gestao_escola.model.Nota;
import com.weg.gestao_escola.model.NotaResposta;

public class NotaMapper {

    public Nota paraEntidade(NotaRequisicaoDTO requisicaoDTO){
        return new Nota(requisicaoDTO.alunoId(), requisicaoDTO.aulaId(), requisicaoDTO.valor());
    }

    public NotaRespostaDTO paraRespostaDTO(NotaResposta nota){
        return new NotaRespostaDTO(nota.getId(), nota.getAlunoNome(), nota.getAulaAssunto(), nota.getValor());
    }

    public Nota paraUpdate(Nota nota, NotaRequisicaoDTO requisicaoDTO){
        if(requisicaoDTO.alunoId() != nota.getAlunoId()){
            nota.setAlunoId(requisicaoDTO.alunoId());
        }

        if(requisicaoDTO.aulaId() != nota.getAulaId()){
            nota.setAulaId(requisicaoDTO.aulaId());
        }

        if(requisicaoDTO.valor() != nota.getValor()){
            nota.setValor(requisicaoDTO.valor());
        }

        return nota;
    }
}
