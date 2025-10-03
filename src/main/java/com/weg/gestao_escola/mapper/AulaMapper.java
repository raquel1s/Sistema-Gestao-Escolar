package com.weg.gestao_escola.mapper;

import com.weg.gestao_escola.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escola.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escola.model.Aula;
import com.weg.gestao_escola.model.AulaResposta;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(AulaRequisicaoDTO requisicaoDTO){
        return new Aula(requisicaoDTO.turmaId(), requisicaoDTO.dataHora(), requisicaoDTO.assunto());
    }

    public AulaRespostaDTO paraRespostaDTO(AulaResposta aula){
        return new AulaRespostaDTO(aula.getId(),
                aula.getNomeTurma(),
                aula.getDataHora(),
                aula.getAssunto());
    }

    public Aula paraUpdate(AulaRequisicaoDTO requisicaoDTO, Aula aula){
        if(requisicaoDTO.turmaId() != aula.getTurmaId() && requisicaoDTO.turmaId() != 0){
            aula.setTurmaId(requisicaoDTO.turmaId());
        }

        if(requisicaoDTO.dataHora() != aula.getDataHora() && requisicaoDTO.dataHora() != null){
            aula.setDataHora(requisicaoDTO.dataHora());
        }

        if(!requisicaoDTO.assunto().equals(aula.getAssunto()) && requisicaoDTO.assunto() != null){
            aula.setAssunto(requisicaoDTO.assunto());
        }

        return aula;
    }
}
