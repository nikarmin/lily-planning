package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Aluno;
import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Professor;
import com.example.applilyplanning.model.ToDo;
import com.example.applilyplanning.model.Token;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoList extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdaptador taskAdapter;
    EditText edtNewTask;
    Button btnNewTask;
    ImageButton btnDatePicker, ibtnMateria;
    TextView selectedDateTV;
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
        ibtnMateria = findViewById(R.id.ibtnMaterias);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");

        ibtnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoList.this, Materias.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        AppCompatButton button = findViewById(R.id.btnAdicionar);

        builder = new AlertDialog.Builder(TodoList.this).setCustomTitle(titleView);
        builder.setCancelable(true);
        View v = LayoutInflater.from(TodoList.this).inflate(R.layout.new_task, null, false);
        //View v2 = LayoutInflater.from(TodoList.this).inflate(R.layout.task, null, false);
        builder.setView(v);

        dialog = builder.create();

        //String aluno = params.getString("email_aluno");
        // Aluno alunoRecebido = (Aluno) getIntent().getSerializableExtra("aluno");

        if (params != null)
        {
            Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();

                    // EXCLUIR NOTIFICAÇÃO

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

                    btnDatePicker = v.findViewById(R.id.idBtnPickDate);
                    selectedDateTV = v.findViewById(R.id.idTVSelectedDate);

                    btnDatePicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                            Date d = new Date();
                            try {
                                d = sdf.parse(String.valueOf(d));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(TodoList.this, d.toString(), Toast.LENGTH_SHORT).show();

                            // on below line we are getting
                            // the instance of our calendar.
                            final Calendar c = Calendar.getInstance();

                            // on below line we are getting
                            // our day, month and year.
                            int year = c.get(Calendar.YEAR);
                            int month = c.get(Calendar.MONTH);
                            int day = c.get(Calendar.DAY_OF_MONTH);

                            // on below line we are creating a variable for date picker dialog.
                            DatePickerDialog datePickerDialog = new DatePickerDialog(
                                    // on below line we are passing context.
                                    TodoList.this,new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            // on below line we are setting date to our text view.
                                            selectedDateTV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                            
//                                            string textinho = selectedDateTV.getText();
//                                            selectedDateTV.setText(textinho + );

                                        }
                                    },
                                    // on below line we are passing year,
                                    // month and day for selected date in our date picker.
                                    year, month, day);
                            // at last we are calling show to
                            // display our date picker dialog.
                            datePickerDialog.show();
                        }
                    });


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