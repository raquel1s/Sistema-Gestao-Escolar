package com.weg.gestao_escola.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Aula {

    private int id;
    private int turmaId;
    private LocalDateTime data_hora;
    private String assunto;

    public Aula(int id, int turmaId, LocalDateTime data_hora, String assunto) {
        this.id = id;
        this.turmaId = turmaId;
        this.data_hora = data_hora;
        this.assunto = assunto;
    }

    public Aula(int turmaId, LocalDateTime data_hora, String assunto) {
        this.turmaId = turmaId;
        this.data_hora = data_hora;
        this.assunto = assunto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
