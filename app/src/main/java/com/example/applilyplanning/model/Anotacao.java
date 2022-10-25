package com.example.applilyplanning.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Anotacao implements Serializable {
    private int idAnotacao;
    private int fkAluno;
    private String anotacao;

    public Anotacao(int idAnotacao, String anotacao) {
        this.idAnotacao = idAnotacao;
        this.anotacao = anotacao;
    }

    public Anotacao(String anotacao, int fkAluno) {
        this.anotacao = anotacao;
        this.fkAluno = fkAluno;
    }

    public int getIdAnotacao() {
        return idAnotacao;
    }

    public void setIdAnotacao(int idAnotacao) {
        this.idAnotacao = idAnotacao;
    }

    public int getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(int fkAluno) {
        this.fkAluno = fkAluno;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
}
