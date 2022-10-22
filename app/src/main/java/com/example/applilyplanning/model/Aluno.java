package com.example.applilyplanning.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Aluno implements Serializable {
    @SerializedName("nome_aluno")
    private String nome_aluno;
    @SerializedName("email_aluno")
    private String email_aluno;
    @SerializedName("senha_aluno")
    private String senha_aluno;

    public Integer getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Integer id_aluno) {
        this.id_aluno = id_aluno;
    }

    @SerializedName("id_aluno")
    private Integer id_aluno;

    public Aluno (String nome, String email, String senha){
        this.nome_aluno = nome;
        this.senha_aluno = senha;
        this.email_aluno = email;
    }

    public Aluno (String email, String senha){
        this.senha_aluno = senha;
        this.email_aluno = email;
    }

    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getEmail_aluno() {
        return email_aluno;
    }

    public void setEmail_aluno(String email_aluno) {
        this.email_aluno = email_aluno;
    }

    public String getSenha_aluno() {
        return senha_aluno;
    }

    public void setSenha_aluno(String senha_aluno) {
        this.senha_aluno = senha_aluno;
    }
}
