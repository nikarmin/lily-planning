package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Aluno;

import retrofit2.Call;

public class Login extends AppCompatActivity {

    EditText email, senha;
    Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        entrar = findViewById(R.id.btnEntrar);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        String user = params.getString("key_user");

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == "Professor"){

                }

                if (user == "Aluno"){
                    if (!(email.getText() == null) || !(senha.getText() == null))
                    {
                        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                        Call<Aluno> call = service.incluirAluno(aluno);
                    }
                }
            }
        });

    }
}