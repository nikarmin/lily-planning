package com.example.applilyplanning;

import android.media.Image;

import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Imagem;
import com.example.applilyplanning.model.Professor;
import com.example.applilyplanning.model.Token;

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

    @GET("/alunos/{id_aluno}")
    Call<Aluno> selecionarAlunoId(@Path("id_aluno") Integer id);

    @GET("/alunos/{email_aluno}")
    Call<Aluno> selecionarAluno(@Path("email_aluno") String email);

    @GET("/alunos/token/{token}")
    Call<Aluno> selecionarAlunoPorToken(@Path("token") String token);

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

    @GET("anotacoes/date/{fk_aluno}")
    Call<List<Anotacao>> selecionarAnotacaoDate(@Path("fk_aluno") Integer id);

    @GET("/anotacoes/{fk_aluno}")
    Call<List<Anotacao>> selecionarAnotacaoFk(@Path("fk_aluno") Integer id);

    @GET("/anotacoes/{id_anotacao}")
    Call<Anotacao> selecionarAnotacao(@Path("id_anotacao") String id);

    @POST("/anotacoes")
    Call<Anotacao> incluirAnotacao(@Body Anotacao anotacao);

    @PUT("/anotacoes/{id_anotacao}")
    Call<Anotacao> alterarAnotacao(@Path("id_anotacao") String id, @Body Anotacao anotacao);

    @DELETE("/anotacoes/{id_anotacao}")
    Call<Anotacao> excluirAnotacao(@Path("id_anotacao") Integer id_anotacao);

    // API DAS IMAGENS

    @GET("/upload")
    Call<List<Imagem>> getImagens();

    @GET("/upload/{id_upload}")
    Call<List<Anotacao>> selecionarImagem();

    @POST("/upload")
    Call<Imagem> postImagem(@Body Imagem imagem);

    @DELETE("/upload/{id_upload}")
    Call<Imagem> excluirImagem(@Path("id_upload") Integer id_upload);
}
