package com.weg.gestao_escola.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Aula {

    private int id;
    private int turmaId;
    private LocalDateTime dataHora;
    private String assunto;

    public Aula(int id, int turmaId, LocalDateTime dataHora, String assunto) {
        this.id = id;
        this.turmaId = turmaId;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Aula(int turmaId, LocalDateTime dataHora, String assunto) {
        this.turmaId = turmaId;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Aula(int id, String assunto, LocalDateTime dataHora) {
        this.id = id;
        this.dataHora = dataHora;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime data_hora) {
        this.dataHora = data_hora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
