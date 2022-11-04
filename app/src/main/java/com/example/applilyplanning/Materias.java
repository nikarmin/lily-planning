package com.example.applilyplanning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Materia;

import java.util.ArrayList;
import java.util.List;

public class Materias extends AppCompatActivity {

    private RecyclerView subjectRecyclerView, notaMateriaRecyclerView;
    private MateriaAdaptador materiaAdaptador;
    Button btnNewSubject, btnPlus;
    EditText edtNewSubjectName, edtNewSubjectColor;
    ImageButton ibtnTodoList;

    AlertDialog.Builder builder;
    AlertDialog dialog;
    private List<Materia> listaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");

        listaMaterias = new ArrayList<Materia>();

        ArrayList<Materia> listaMateriasTemporarias = new ArrayList<Materia>();
        Materia materiaTest = new Materia("física quântica aplicada", "#646464");
        listaMateriasTemporarias.add(materiaTest);

        AppCompatButton button = findViewById(R.id.btnAdicionar);
        ibtnTodoList = findViewById(R.id.ibtnTodoListPage);

        ibtnTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Materias.this, TodoList.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        View v = LayoutInflater.from(this).inflate(R.layout.new_subject, null, false);
        builder.setView(v);
        dialog = builder.create();

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View view) {
                dialog.show();

                btnNewSubject = v.findViewById(R.id.btnNewSubject);
                btnNewSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        edtNewSubjectColor = v.findViewById(R.id.edtNewSubjectColor);
                        edtNewSubjectName = v.findViewById(R.id.edtNewSubjectName);

                        Materia mat = new Materia(edtNewSubjectName.getText().toString(), edtNewSubjectColor.getText().toString());
                        //listaMateriasTemporarias.add(mat);
                        listaMaterias.add(mat);
                        dialog.dismiss();

                        edtNewSubjectName.setText("");
                        edtNewSubjectColor.setText("");
                        //AtualizarLista(listaMateriasTemporarias);
                    }
                });
            }
        });
        //AtualizarLista(listaMateriasTemporarias);


        materiaAdaptador = new MateriaAdaptador(listaMaterias);
        materiaAdaptador.setSubjects(listaMaterias);

        subjectRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMaterias);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        subjectRecyclerView.setLayoutManager(manager);
        subjectRecyclerView.setAdapter(materiaAdaptador);

        materiaAdaptador.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Anotacao userData) {
            }

            @Override
            public void onItemClick(int position, Materia userData) {
                builder = new AlertDialog.Builder(Materias.this);
                builder.setCancelable(false);
                View view = LayoutInflater.from(Materias.this).inflate(R.layout.notas_materia, null, false); //caixa de diálogo
                initUpdateDialog(position, view);
                builder.setView(view); //seta a caixa de diálogo
                dialog = builder.create(); //cria a caixa
                dialog.show(); //mostra a caixa de diálogo
            }
        });
    }

    public void AtualizarLista(ArrayList<Materia> lista)
    {
        for (Materia materia:lista){
            listaMaterias.add(materia);
        }
    }

    private void initUpdateDialog(int position, View view){
        notaMateriaRecyclerView = view.findViewById(R.id.recyclerViewNotasMateria);
        btnPlus = view.findViewById(R.id.btnPlus);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, NotaCompleta.class));
            }
        });
    }
}