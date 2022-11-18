package com.example.applilyplanning.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Imagem implements Serializable {
    @SerializedName("url_foto")
    private String imagem;
    @SerializedName("id_upload")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Imagem(Uri imagem) {
        this.imagem = String.valueOf(imagem);
    }

    public Imagem(Uri imagem, int id) {
        this.imagem = String.valueOf(imagem);
        this.id = id;
    }

    public Imagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

}
