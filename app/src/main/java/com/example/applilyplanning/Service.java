package com.example.applilyplanning;

import com.example.applilyplanning.model.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    @GET("/alunos")
    Call<List<Aluno>> getAluno();           // retorna uma lista de cachorro

    @GET("/alunos/{id}")
    Call<Aluno> selecionarAluno(@Path("nome") String id);        // retorna uma UNIDADE de cachorro, pelo NOME <<<<<<<<<<

    @POST("/alunos")
    Call<Aluno> incluirAluno(@Body Aluno aluno);            // incluir cachorro

    @PUT("/alunos/{id}")
    Call<Aluno> alterarAluno(@Path("id") String id, @Body Aluno aluno);          // alterar UMA UNIDADE DE CACHORRO

    @DELETE("/alunos")
    Call<Aluno> excluirAluno(@Path("id") String id);            // deletar UMA UNIDADE DE CACHORRO
}
