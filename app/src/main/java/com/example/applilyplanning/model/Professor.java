package com.example.applilyplanning.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Professor implements Serializable {

    @SerializedName("nome_professor")
    private String nome_professor;
    @SerializedName("email_professor")
    private String email_professor;
    @SerializedName("senha_professor")
    private String senha_professor;

    public Professor (String nome, String email, String senha){
        this.nome_professor = nome;
        this.senha_professor = senha;
        this.email_professor = email;
    }

    public Professor (String email, String senha){
        this.senha_professor = senha;
        this.email_professor = email;
    }

    public String getNome_professor() {
        return nome_professor;
    }

    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }

    public String getEmail_professor() {
        return email_professor;
    }

    public void setEmail_professor(String email_professor) {
        this.email_professor = email_professor;
    }

    public String getSenha_professor() {
        return senha_professor;
    }

    public void setSenha_professor(String senha_professor) {
        this.senha_professor = senha_professor;
    }
}
