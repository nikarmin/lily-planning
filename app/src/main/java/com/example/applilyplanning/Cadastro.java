package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Professor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity
{
    ImageButton todo;
    AppCompatButton btn;
    EditText email, senha, nome;
    CheckBox chkProfessor, chkAluno;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        nome = findViewById(R.id.edtNomeUsuario);
        btnCadastrar = findViewById(R.id.btnEntrar);
        chkProfessor = findViewById(R.id.chkProfessor);
        chkAluno = findViewById(R.id.chkAluno);

        chkAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkProfessor.setChecked(false);
            }
        });

        chkProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkAluno.setChecked(false);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (email != null && senha != null && nome != null && chkAluno.isChecked()){
                    Aluno aluno = new Aluno(nome.getText().toString(), email.getText().toString(), senha.getText().toString());

                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                    Call<Aluno> call = service.incluirAluno(aluno);

                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            if (response.isSuccessful())
                            {
                                Aluno alunoResponse = response.body();

                                alunoResponse.getNome_aluno();
                                alunoResponse.getEmail_aluno();
                                alunoResponse.getSenha_aluno();

                                Intent intent = new Intent(Cadastro.this, Login.class);
                                Bundle bundle = new Bundle();

                                bundle.putString("key_user", "Aluno");

                                intent.putExtras(bundle);

                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Cadastro.this, "Erro na inclusão!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                            Toast.makeText(Cadastro.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (email != null && senha != null && nome != null && chkProfessor.isChecked()){
                    Professor professor = new Professor(nome.getText().toString(), email.getText().toString(), senha.getText().toString());

                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                    Call<Professor> call = service.incluirProfessor(professor);

                    call.enqueue(new Callback<Professor>() {
                        @Override
                        public void onResponse(Call<Professor> call, Response<Professor> response) {
                            if (response.isSuccessful())
                            {
                                Professor professorResponse = response.body();

                                professorResponse.getNome_professor();
                                professorResponse.getEmail_professor();
                                professorResponse.getSenha_professor();

                                Intent intent = new Intent(Cadastro.this, Login.class);
                                Bundle bundle = new Bundle();

                                bundle.putString("key_user", "Professor");

                                intent.putExtras(bundle);

                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Cadastro.this, "Erro na inclusão!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Professor> call, Throwable t) {
                            Toast.makeText(Cadastro.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Cadastro.this, "Não deixe os campos em branco!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}