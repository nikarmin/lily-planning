package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Professor;
import com.example.applilyplanning.model.Token;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText email, senha;
    Button entrar;
    CheckBox chkAluno, chkProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        entrar = findViewById(R.id.btnEntrar);
        chkAluno = findViewById(R.id.chkAluno);
        chkProfessor = findViewById(R.id.chkProfessor);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        String user = "";

        if (params != null){
            user = params.getString("key_user");

            if (user.equals("Aluno"))
                chkAluno.setChecked(true);
            else
                chkProfessor.setChecked(true);
        }

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

        String finalUser = user;
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (chkAluno.isChecked())
                    {
                        if ((email.getText() != null) && (senha.getText() != null))
                        {
                            Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                            Aluno aluno = new Aluno(email.getText().toString(), senha.getText().toString());
                            Call<Aluno> call = service.verificarAluno(aluno);

                            call.enqueue(new Callback<Aluno>() {
                                @Override
                                public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                                    if (response.isSuccessful()){
                                        Aluno aluno = response.body();

                                        Bundle params = new Bundle();
                                        Intent intent = new Intent(Login.this, Calendario.class);

                                        params.putInt("token", aluno.getId_aluno());
                                        //params.putString("key_user", "Aluno");

                                        intent.putExtras(params);

                                        startActivity(intent);
                                    }
                                     else {
                                        Toast.makeText(Login.this, "Verifique suas credenciais!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Aluno> call, Throwable t) {
                                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else
                            Toast.makeText(Login.this, "Digite em todos os campos!", Toast.LENGTH_SHORT).show();
                    }
                    else if (chkProfessor.isChecked())
                    {
                        if ((email.getText() != null) && (senha.getText() != null))
                        {
                            Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                            Professor professor = new Professor(email.getText().toString(), senha.getText().toString());

                            Call<Professor> call = service.verificarProfessor(professor);

                            call.enqueue(new Callback<Professor>() {
                                @Override
                                public void onResponse(Call<Professor> call, Response<Professor> response) {
                                    if (response.isSuccessful()){
                                        Professor professorResponse = response.body();

                                        professorResponse.getEmail_professor();
                                        professorResponse.getSenha_professor();

                                        params.putInt("token", professor.getId_professor());
                                        //params.putString("key_user", "Professor");

                                        intent.putExtras(params);

                                        startActivity(new Intent(Login.this, Calendario.class));
                                    }
                                    else {
                                        Toast.makeText(Login.this, "Verifique suas credenciais!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Professor> call, Throwable t) {
                                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else
                            Toast.makeText(Login.this, "Digite em todos os campos!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Login.this, "Selecione/digite em todos os campos!", Toast.LENGTH_SHORT).show();
                }
        });

    }
}