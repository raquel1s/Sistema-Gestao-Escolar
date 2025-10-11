package com.weg.gestao_escola.model;

public class NotaResposta extends Nota{

    private String alunoNome;
    private String aulaAssunto;

    public NotaResposta(int id, double valor, String alunoNome, String aulaAssunto) {
        super(id, valor);
        this.alunoNome = alunoNome;
        this.aulaAssunto = aulaAssunto;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAulaAssunto() {
        return aulaAssunto;
    }

    public void setAulaAssunto(String aulaAssunto) {
        this.aulaAssunto = aulaAssunto;
    }
}
