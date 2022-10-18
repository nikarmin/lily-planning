package com.example.applilyplanning.model;

import java.util.Date;

public class ToDo {
    private String toDoLabel;
    private Date dataInicio, dataEntrega;
    private int idLista, fkAnotacao;
    private String nomeLista;

    public ToDo(String toDoLabel) {
        this.toDoLabel = toDoLabel;
    }

    public String getToDoLabel() {
        return toDoLabel;
    }

    public void setToDoLabel(String toDoLabel) {
        this.toDoLabel = toDoLabel;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getFkAnotacao() {
        return fkAnotacao;
    }

    public void setFkAnotacao(int fkAnotacao) {
        this.fkAnotacao = fkAnotacao;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
}
