package com.weg.gestao_escola.model;

public class TurmaResposta extends Turma{

    private String nomeCurso;
    private String nomeProfessor;

    public TurmaResposta(int id, String nome, String nomeCurso, String nomeProfessor) {
        super(id, nome);
        this.nomeCurso = nomeCurso;
        this.nomeProfessor = nomeProfessor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }
}
