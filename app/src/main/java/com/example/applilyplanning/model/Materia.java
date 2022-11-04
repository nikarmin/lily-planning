package com.example.applilyplanning.model;

import java.io.Serializable;

public class Materia implements Serializable {
    private String nomeMateria, corMateria;

    public Materia(String nomeMateria, String corMateria) {
        this.nomeMateria = nomeMateria;
        this.corMateria = corMateria;
    }

    // método get pro nomeMateria
    public String getNomeMateria(){
        return this.nomeMateria;
    }

    // método set pro nomeMateria
    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    // método get pro corMateria
    public String getCorMateria(){
        return this.corMateria;
    }

    public void setCorMateria(String corMateria){
        this.corMateria = corMateria;
    }

    /*public void IrHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/

}