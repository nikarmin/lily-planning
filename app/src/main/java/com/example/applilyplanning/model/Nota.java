package com.example.applilyplanning.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Nota implements Serializable{
    @SerializedName("tituloNota")
    private String tituloNota;
    @SerializedName("dataNota")
    private String dataNota;

    public Nota(String tituloNota, String dataNota){
        this.dataNota = dataNota;
        this.tituloNota = tituloNota;
    }

    public Nota() {}

    // método get pro tituloNota
    public String getTituloNota() {
        return this.tituloNota;
    }

    // método set pro tituloNota
    public void setTituloNota(String tituloNota) {
        this.tituloNota = tituloNota;
    }

    // método get pro dataNota
    public String getDataNota() {
        return dataNota;
    }

    // método set pro dataNota
    public void setDataNota(String dataNota) {
        this.dataNota = dataNota;
    }
}