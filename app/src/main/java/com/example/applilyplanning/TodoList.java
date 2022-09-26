package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.applilyplanning.model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class TodoList extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdaptador taskAdapter;

    private List<ToDoModel> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        listaTarefas = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.recyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new ToDoAdaptador(this);
        taskRecyclerView.setAdapter(taskAdapter);

        ToDoModel task = new ToDoModel();
        //task.setDataInicio("24/09/2022");
        //task.setDataEntrega("25/09/2022");
        task.setNomeLista("Teste lista");
        //task.setStatus(0);
        //.setIdLista(1);

        listaTarefas.add(task);
        listaTarefas.add(task);
        listaTarefas.add(task);
        listaTarefas.add(task);
        listaTarefas.add(task);

        taskAdapter.setTasks(listaTarefas);
    }

    public void IrHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}