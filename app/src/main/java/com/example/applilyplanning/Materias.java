package com.example.applilyplanning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Materia;

import java.util.ArrayList;
import java.util.List;


public class Materias extends AppCompatActivity {

    private RecyclerView subjectRecyclerView, notaMateriaRecyclerView;
    private MateriaAdaptador materiaAdaptador;
    Button btnNewSubject, btnPlus;
    EditText edtNewSubjectName, edtNewSubjectColor, edtNotaa, edtNewNoteTittle, edtNewNoteDate;

    AlertDialog.Builder builder;
    AlertDialog dialog;
    private List<Materia> listaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        listaMaterias = new ArrayList<Materia>();

        ArrayList<Materia> listaMateriasTemporarias = new ArrayList<Materia>();
        Materia materiaTest = new Materia("física quântica aplicada", "#646464");
        listaMateriasTemporarias.add(materiaTest);

        AppCompatButton button = findViewById(R.id.btnAdicionar);

        builder = new AlertDialog.Builder(Materias.this);

        builder.setCancelable(true);
        View v = LayoutInflater.from(Materias.this).inflate(R.layout.new_subject, null, false);
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
                        listaMateriasTemporarias.add(mat);
                        dialog.dismiss();

                        edtNewSubjectName.setText("");
                        edtNewSubjectColor.setText("");
                    }
                });

                for (Materia materia : listaMateriasTemporarias){
                    listaMaterias.add(materia);
                }
            }
        });

        materiaAdaptador.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Anotacao userData) {

            }

            @Override
            public void onItemClick(int position, Materia userData) {
                builder = new AlertDialog.Builder(Materias.this);
                builder.setTitle("Visualizar matéria");
                builder.setCancelable(false);
                View view = LayoutInflater.from(Materias.this).inflate(R.layout.notas_materia, null, false); //caixa de diálogo

                Button btnPlus = view.findViewById(R.id.btnPlus);

                btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builderAnotacoesMateria = new AlertDialog.Builder(Materias.this);
                        builderAnotacoesMateria.setCancelable(true);
                       // View v = LayoutInflater.from(Materias.this).inflate(R.layout.new_anotacao_materia, null, false);
                        builderAnotacoesMateria.setView(v);
                        AlertDialog dialogAnotacoesMateria = builderAnotacoesMateria.create();
                        dialogAnotacoesMateria.show();


                    }
                });

                initUpdateDialog(position, view);
                builder.setView(view); //seta a caixa de diálogo
                dialog = builder.create(); //cria a caixa
                dialog.show(); //mostra a caixa de diálogo
            }
        });




        materiaAdaptador = new MateriaAdaptador(listaMaterias);
        materiaAdaptador.setSubjects(listaMaterias);

        subjectRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMaterias);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        subjectRecyclerView.setLayoutManager(manager);
        subjectRecyclerView.setAdapter(materiaAdaptador);

    }

    /*private void initWidgets(){
        edtNotaa = findViewById(R.id.edtNotaa);
        edtNewNoteTittle = findViewById(R.id.edtNewNoteTittle);
        edtNewNoteDate = findViewById(R.id.edtNewNoteDate);
    }

    private void saveNewNote(View view){
        String tituloNota = edtNewNoteTittle.getText().toString();
        String dataNota = edtNewNoteDate.getText().toString();
        String notaa = edtNotaa.getText().toString();

    }*/

    public void IrHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initUpdateDialog(int position, View view){
        notaMateriaRecyclerView.findViewById(R.id.recyclerViewNotasMateria);
//        btnPlus.findViewById(R.id.btnPlus);
//
//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Materias.this, AnotacoesMateria.class));
//            }
//        });
    }
}