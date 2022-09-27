package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applilyplanning.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TodoList extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdaptador taskAdapter;
    EditText edtNewTask;
    Button btnNewTask;

    List<ToDo> listaDeTarefas;

    AlertDialog.Builder builder;

    AlertDialog dialog;

    private List<ToDo> listaTarefas;

    public class StartGameDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("eai mano brown ")
                    .setPositiveButton("opa", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // START THE GAME!
                        }
                    })
                    .setNegativeButton("sai fora", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        listaDeTarefas = new ArrayList<ToDo>();

        ArrayList<String> tarefasAnteriores = new ArrayList<String>(); // Temporário
        tarefasAnteriores.add("Fazer chicão");
        tarefasAnteriores.add("Fazer API de práticas");
        tarefasAnteriores.add("Estudar Português");



        AppCompatButton button = findViewById(R.id.btnAdicionar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(TodoList.this);
                //builder.setTitle("Alterar usuário");
                builder.setCancelable(false);
                View v = LayoutInflater.from(TodoList.this).inflate(R.layout.new_task, null, false);
                builder.setView(v);
                dialog = builder.create();
                dialog.show();
                btnNewTask = v.findViewById(R.id.btnNewTask);
                btnNewTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtNewTask = v.findViewById(R.id.edtNewTask);
                        //Toast.makeText(TodoList.this, edtNewTask.getText(), Toast.LENGTH_SHORT).show();
                        tarefasAnteriores.add(edtNewTask.getText().toString());
                        dialog.dismiss();
                    }
                });
            }
        });

        for (String toDo : tarefasAnteriores) {
            listaDeTarefas.add(new ToDo(toDo));
        }

        taskAdapter = new ToDoAdaptador(listaDeTarefas);
        taskRecyclerView = findViewById(R.id.recyclerView);
        taskRecyclerView.setAdapter(taskAdapter);

        /*
        listaTarefas = new ArrayList<>();

        taskRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new ToDoAdaptador(listaTarefas);
        taskRecyclerView.setAdapter(taskAdapter);

        ToDoModel task = new ToDoModel();
        //task.setDataInicio("24/09/2022");
        //task.setDataEntrega("25/09/2022");
        task.setNomeLista("Teste lista");
        //task.setStatus(0);
        //task.setIdLista(1);

        listaTarefas.add(task);

        taskAdapter.setTasks(listaTarefas);
        taskAdapter.notifyDataSetChanged();

         */
    }

    public void IrHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}