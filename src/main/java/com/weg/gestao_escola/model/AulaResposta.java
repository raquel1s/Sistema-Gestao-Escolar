package com.weg.gestao_escola.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AulaResposta extends Aula{

    private String nomeTurma;

    public AulaResposta(int id, String nomeTurma, LocalDateTime dataHora, String assunto) {
        super(id, assunto, dataHora);
        this.nomeTurma = nomeTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
}
