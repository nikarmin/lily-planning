package com.example.applilyplanning.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class AnotacaoMateria implements Serializable{
    private String tituloAnotacaoMateria;
    private String dataAnotacaoMateria;
    private String nota;

    public AnotacaoMateria(String tituloAnotacaoMateria, String dataAnotacaoMateria){
        this.tituloAnotacaoMateria = tituloAnotacaoMateria;
        this.dataAnotacaoMateria = dataAnotacaoMateria;
    }

    public AnotacaoMateria(String tituloAnotacaoMateria, String dataAnotacaoMateria, String nota){
        this.tituloAnotacaoMateria = tituloAnotacaoMateria;
        this.dataAnotacaoMateria = dataAnotacaoMateria;
        this.nota = nota;
    }

    public String getTituloAnotacaoMateria(){
        return this.tituloAnotacaoMateria;
    }

    public void setTituloAnotacaoMateria(String dataAnotacaoMateria){
        this.tituloAnotacaoMateria = tituloAnotacaoMateria;
    }

    public String getDataAnotacaoMateria(){
        return this.dataAnotacaoMateria;
    }

    public void setDataAnotacaoMateria(String dataAnotacaoMateria){
        this.dataAnotacaoMateria = dataAnotacaoMateria;
    }
}