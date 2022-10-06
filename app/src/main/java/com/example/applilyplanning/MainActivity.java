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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton todo;
    AppCompatButton btn;
    EditText email, senha, nome;
    CheckBox chkProfessor, chkAluno;
    Button btnCadastrar;

    // OXI PQ????????????????? VVVVVVVVV CANALHASSSS
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        nome = findViewById(R.id.edtNomeUsuario);
        btnCadastrar = findViewById(R.id.btnEntrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email != null && senha != null && nome != null && (chkAluno.isActivated() || chkAluno.isActivated())){
                    Aluno aluno = new Aluno(nome.getText().toString(), email.getText().toString(), senha.getText().toString());
                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                    Call<Aluno> call = service.incluirAluno(aluno);

                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            if (response.isSuccessful()){
                                Aluno alunoResponse = response.body();

                                alunoResponse.getNome_aluno();
                                alunoResponse.getEmail_aluno();
                                alunoResponse.getSenha_aluno();

                                Gson gson = new GsonBuilder().create();

                                String jsonResponse = gson.toJson(alunoResponse);
                            }
                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Deu errado!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        /*todo = (ImageButton) findViewById(R.id.ibtnTodoListPage);

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, TodoList.class);

                    startActivity(intent);
                }
                catch (Exception error){

                }
            }
        });*/
    }
}