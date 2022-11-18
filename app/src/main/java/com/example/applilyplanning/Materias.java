package com.example.applilyplanning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.AnotacaoMateria;
import com.example.applilyplanning.model.Materia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Materias extends AppCompatActivity {

    private RecyclerView subjectRecyclerView, notaMateriaRecyclerView;
    private MateriaAdaptador materiaAdaptador;
    private AnotacaoMateriaAdaptador anotacaoMateriaAdaptador;
    Button btnNewSubject, btnPlus, btnSalvar;
    EditText edtNewSubjectName, edtNewSubjectColor;
    EditText edtNotaa, edtNewNoteTittle, edtNewNoteDate;
    ImageButton ibtnUpload, ibtnTodoList;
    FrameLayout home;

    AlertDialog.Builder builder;
    AlertDialog dialog;
    private List<Materia> listaMaterias;
    private List<AnotacaoMateria> listaNotMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");

        listaMaterias = new ArrayList<Materia>();
        listaNotMateria = new ArrayList<AnotacaoMateria>();

        ArrayList<Materia> listaMateriasTemporarias = new ArrayList<Materia>();
        Materia materiaTest = new Materia("física quântica aplicada", "#646464");
        listaMateriasTemporarias.add(materiaTest);

        AppCompatButton button = findViewById(R.id.btnAdicionar);

        home = findViewById(R.id.iconMenu);
        ibtnUpload = findViewById(R.id.ibtnUpload);
        ibtnTodoList = findViewById(R.id.ibtnTodoListPage);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Materias.this, Calendario.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

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

        ibtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Materias.this, Upload.class);
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
                builder.setCancelable(true);
                View view = LayoutInflater.from(Materias.this).inflate(R.layout.notas_materia, null, false); //caixa de diálogo

                Button btnPlus = view.findViewById(R.id.btnPlus);
                notaMateriaRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNotasMateria);

                for (AnotacaoMateria notaMateria : listaNotMateria) {
                    Log.i("anotação", notaMateria.getTituloAnotacaoMateria());
                }

                anotacaoMateriaAdaptador = new AnotacaoMateriaAdaptador(listaNotMateria);
                anotacaoMateriaAdaptador.setNotes(listaNotMateria);

                LinearLayoutManager manager = new LinearLayoutManager(Materias.this);
                notaMateriaRecyclerView.setLayoutManager(manager);
                notaMateriaRecyclerView.setAdapter(anotacaoMateriaAdaptador);

                btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builderAnotacoesMateria = new AlertDialog.Builder(Materias.this);
                        builderAnotacoesMateria.setCancelable(true);
                        View v = LayoutInflater.from(Materias.this).inflate(R.layout.new_anotacao_materia, null, false);
                        builderAnotacoesMateria.setView(v);
                        AlertDialog dialogAnotacoesMateria = builderAnotacoesMateria.create();
                        dialogAnotacoesMateria.show();

                        btnSalvar = v.findViewById(R.id.btnSalvar);
                        btnSalvar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view){
                                edtNewNoteTittle = v.findViewById(R.id.edtNewNoteTittle);
                                edtNewNoteDate = v.findViewById(R.id.edtNewNoteDate);
                                edtNotaa = v.findViewById(R.id.edtNotaa);

                                AnotacaoMateria notMat = new AnotacaoMateria(edtNewNoteTittle.getText().toString(), edtNewNoteDate.getText().toString(),edtNotaa.getText().toString());
                                //listaMateriasTemporarias.add(mat);
                                listaNotMateria.add(notMat);
                                for (AnotacaoMateria notaMateria : listaNotMateria) {
                                    Log.i("anotação", notaMateria.getTituloAnotacaoMateria());
                                }
                                dialogAnotacoesMateria.dismiss();

                                edtNewNoteTittle.setText("");
                                edtNewNoteDate.setText("");
                                edtNotaa.setText("");
                                //AtualizarLista(listaMateriasTemporarias);
                            }
                        });


                    }
                });

                initUpdateDialog(position, view);
                builder.setView(view); //seta a caixa de diálogo
                dialog = builder.create(); //cria a caixa
                dialog.show(); //mostra a caixa de diálogo
            }

            @Override
            public void onItemClick(int position, AnotacaoMateria item) {

            }
        });



    }

    public void AtualizarLista(ArrayList<Materia> lista)
    {
        for (Materia materia:lista){
            listaMaterias.add(materia);
        }
    }

    /*
    private void initWidgets(){
        edtNotaa = findViewById(R.id.edtNotaa);
        edtNewNoteTittle = findViewById(R.id.edtNewNoteTittle);
        edtNewNoteDate = findViewById(R.id.edtNewNoteDate);
    }

    private void saveNewNote(View view){
        String tituloNota = edtNewNoteTittle.getText().toString();
        String dataNota = edtNewNoteDate.getText().toString();
        String notaa = edtNotaa.getText().toString();
        Event newEvent = new Event(tituloNota, dataNota, notaa);
        Event.listEvents.add(newEvent);
        finish();
    }*/

    private void initUpdateDialog(int position, View view){
        notaMateriaRecyclerView = view.findViewById(R.id.recyclerViewNotasMateria);
//        btnPlus = view.findViewById(R.id.btnPlus);
//
//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, AnotacoesMateria.class));
//            }
//        });
    }
}