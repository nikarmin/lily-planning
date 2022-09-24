package com.example.applilyplanning.model;

import java.sql.Date;

public class ToDoModel {

    private String dataInicio, dataEntrega;

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdAnotacao() {
        return idAnotacao;
    }

    public void setIdAnotacao(int idAnotacao) {
        this.idAnotacao = idAnotacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    private int idLista, idAnotacao, status;
    private String nomeLista;

    /*
    *
    idLista int primary key not null,
	dataInicio date not null,
	dataEntrega date not null,
	nomeLista varchar(20) not null,
	foreign key (idAnotacao) references Tabela.Anotacao (idAnotacao)
    * */
}
