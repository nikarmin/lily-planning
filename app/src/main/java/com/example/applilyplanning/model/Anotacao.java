package com.example.applilyplanning.model;

public class Anotacao {
    private int idAnotacao;
    private String anotacao;

    public Anotacao(/*int idAnotacao,*/ String anotacao) {
        //this.idAnotacao = idAnotacao;
        this.anotacao = anotacao;
    }

    public int getIdAnotacao() {
        return idAnotacao;
    }

    public void setIdAnotacao(int idAnotacao) {
        this.idAnotacao = idAnotacao;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
}
