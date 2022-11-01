package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Professor;
import com.example.applilyplanning.model.ToDo;
import com.example.applilyplanning.model.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoList extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdaptador taskAdapter;
    EditText edtNewTask;
    Button btnNewTask;
    CheckBox anotacao;

    List<ToDo> listaDeTarefas;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    private List<ToDo> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todo_list);
        listaDeTarefas = new ArrayList<ToDo>();

        LayoutInflater inflater = this.getLayoutInflater();
        View titleView = inflater.inflate(R.layout.alert_task, null);

//        ArrayList<String> tarefasAnteriores = new ArrayList<String>(); // Temporário
//        tarefasAnteriores.add("Fazer chicão");
//        tarefasAnteriores.add("Fazer API de práticas");
//        tarefasAnteriores.add("Estudar Português");

        AppCompatButton button = findViewById(R.id.btnAdicionar);

        builder = new AlertDialog.Builder(TodoList.this).setCustomTitle(titleView);
        builder.setCancelable(true);
        View v = LayoutInflater.from(TodoList.this).inflate(R.layout.new_task, null, false);
        //View v2 = LayoutInflater.from(TodoList.this).inflate(R.layout.task, null, false);
        builder.setView(v);

        dialog = builder.create();

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");

        //String aluno = params.getString("email_aluno");
        // Aluno alunoRecebido = (Aluno) getIntent().getSerializableExtra("aluno");

        if (params != null)
        {
            Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();

                    /*anotacao = v2.findViewById(R.id.chkToDo);

                    anotacao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {

                                }
                            }, 5000); // 5 segundos
                        }
                    });*/

                    btnNewTask = v.findViewById(R.id.btnNewTask);

                    btnNewTask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            edtNewTask = v.findViewById(R.id.edtNewTask);

                            String anotation = edtNewTask.getText().toString();

                            Anotacao anotacao = new Anotacao(anotation, tokenRecebido);
                            Call<Anotacao> call = service.incluirAnotacao(anotacao);

                            call.enqueue(new Callback<Anotacao>() {
                                @Override
                                public void onResponse(Call<Anotacao> call, Response<Anotacao> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(TodoList.this, "Anotação incluída!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Anotacao> call, Throwable t) {
                                    Toast.makeText(TodoList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                            dialog.dismiss();
                        }

                    });
                }
            });

            Call<List<Anotacao>> callAnot = service.selecionarAnotacaoFk(tokenRecebido);
            callAnot.enqueue(new Callback<List<Anotacao>>()
                {
                    @Override
                    public void onResponse(Call<List<Anotacao>> call, Response<List<Anotacao>> response)
                    {
                      taskAdapter = new ToDoAdaptador(response.body());
                      taskRecyclerView = findViewById(R.id.recyclerView);
                      taskRecyclerView.setAdapter(taskAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Anotacao>> call, Throwable t)
                    {
                      Toast.makeText(TodoList.this, "talda depressao", Toast.LENGTH_SHORT).show();
                    }
                });
        }
         else
             Toast.makeText(TodoList.this, "deu errado", Toast.LENGTH_SHORT).show();



    }

    public void IrHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}