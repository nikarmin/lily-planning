package com.example.applilyplanning.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Anotacao implements Serializable {
    @SerializedName("id_anotacao")
    private int idAnotacao;
    @SerializedName("fk_aluno")
    private int fk_aluno;
    @SerializedName("anotacao")
    private String anotacao;

    public Anotacao(int idAnotacao, String anotacao) {
        this.idAnotacao = idAnotacao;
        this.anotacao = anotacao;
    }

    public Anotacao(String anotacao, int fkAluno) {
        this.anotacao = anotacao;
        this.fk_aluno = fkAluno;
    }

    public int getIdAnotacao() {
        return idAnotacao;
    }

    public void setIdAnotacao(int idAnotacao) {
        this.idAnotacao = idAnotacao;
    }

    public int getFkAluno() {
        return fk_aluno;
    }

    public void setFkAluno(int fkAluno) {
        this.fk_aluno = fkAluno;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
}
