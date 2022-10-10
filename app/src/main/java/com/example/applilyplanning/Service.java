package com.example.applilyplanning;

import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Professor;

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
    Call<List<Aluno>> getAluno();

    @GET("/alunos/{id}")
    Call<Aluno> selecionarAluno(@Path("nome") String id);

    @POST("/alunos")
    Call<Aluno> incluirAluno(@Body Aluno aluno);

    @PUT("/alunos/{id}")
    Call<Aluno> alterarAluno(@Path("id") String id, @Body Aluno aluno);

    @DELETE("/alunos")
    Call<Aluno> excluirAluno(@Path("id") String id);

    @GET("/professores")
    Call<List<Professor>> getProfessor();

    @GET("/professores/{id}")
    Call<Professor> selecionarProfessor(@Path("nome") String id);

    @POST("/professores")
    Call<Professor> incluirProfessor(@Body Professor professor);

    @PUT("/professores/{id}")
    Call<Professor> alterarProfessor(@Path("id") String id, @Body Professor professor);

    @DELETE("/professores")
    Call<Professor> excluirAProfessor(@Path("id") String id);
}
