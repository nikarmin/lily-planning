package com.example.applilyplanning.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Imagem implements Serializable {
    @SerializedName("url_foto")
    private String imagem;

    public Imagem(Uri imagem) {
        this.imagem = String.valueOf(imagem);
    }

    public Imagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

}
