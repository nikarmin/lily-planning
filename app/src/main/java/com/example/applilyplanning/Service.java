package com.example.applilyplanning;

import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Anotacao;
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
    // API DOS ALUNOS

    @GET("/alunos")
    Call<List<Aluno>> getAluno();

    @GET("/alunos/{email}")
    Call<Aluno> selecionarAluno(@Path("email_aluno") String email);

    @POST("/alunos")
    Call<Aluno> incluirAluno(@Body Aluno aluno);

    @POST("/alunos/auth")
    Call<Aluno> verificarAluno(@Body Aluno aluno);

    @PUT("/alunos/{id}")
    Call<Aluno> alterarAluno(@Path("id") String id, @Body Aluno aluno);

    @DELETE("/alunos")
    Call<Aluno> excluirAluno(@Path("id") String id);

    // API DOS PROFESSORES

    @GET("/professores")
    Call<List<Professor>> getProfessor();

    @GET("/professores/{email}")
    Call<Professor> selecionarProfessor(@Path("email_professor") String id);

    @POST("/professores")
    Call<Professor> incluirProfessor(@Body Professor professor);

    @POST("/professores/auth")
    Call<Professor> verificarProfessor(@Body Professor professor);

    @PUT("/professores/{id}")
    Call<Professor> alterarProfessor(@Path("id") String id, @Body Professor professor);

    @DELETE("/professores")
    Call<Professor> excluirAProfessor(@Path("id") String id);

    // API DAS ANOTAÇÕES

    @GET("/anotacoes")
    Call<List<Anotacao>> getAnotacao();

    @GET("/anotacoes/{id}")
    Call<Anotacao> selecionarAnotacao(@Path("id") String id);

    @POST("/anotacoes")
    Call<Anotacao> incluirAnotacao(@Body Anotacao anotacao);

    @PUT("/anotacoes/{id}")
    Call<Anotacao> alterarAnotacao(@Path("id") String id, @Body Anotacao anotacao);

    @DELETE("/anotacoes")
    Call<Anotacao> excluirAnotacao(@Path("id") String id);
}
