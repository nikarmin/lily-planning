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
import android.widget.FrameLayout;
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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoList extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdaptador taskAdapter;
    EditText edtNewTask;
    Button btnNewTask;
    ImageButton btnDatePicker, ibtnMateria, ibtnUpload;
    TextView selectedDateTV;
    CheckBox anotacao;

    List<ToDo> listaDeTarefas;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    private List<ToDo> listaTarefas;
    String tempoEntrega, tempoAtual;
    FrameLayout home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todo_list);
        listaDeTarefas = new ArrayList<ToDo>();

        LayoutInflater inflater = this.getLayoutInflater();
        View titleView = inflater.inflate(R.layout.alert_task, null);
        ibtnMateria = findViewById(R.id.ibtnMaterias);
        ibtnUpload = findViewById(R.id.ibtnUpload);
        home = findViewById(R.id.iconMenu);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");
        //String user = params.getString("key_user");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoList.this, Calendario.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        ibtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoList.this, Upload.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

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

        if (params != null)
        {
            Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();

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

                            final Calendar c = Calendar.getInstance();

                            int year = c.get(Calendar.YEAR);
                            int month = c.get(Calendar.MONTH);
                            int day = c.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog datePickerDialog = new DatePickerDialog(
                                    // on below line we are passing context.
                                    TodoList.this,new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            // on below line we are setting date to our text view.
                                            selectedDateTV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                            Calendar calendar = Calendar.getInstance();
                                            calendar.set(year, month, day);
                                            final DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);

                                            Date date = null;
                                            try {
                                                Calendar calendario = Calendar.getInstance();
                                                calendario.set(year, monthOfYear, dayOfMonth-1);
                                                date = df.parse(calendario.getTime().toString());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            final Timestamp ts = new Timestamp(date.getTime());
                                            final Timestamp tsAtual = new Timestamp(calendar.getTime().getTime());

                                            tempoEntrega = ts.toString();
                                            tempoAtual  = tsAtual.toString();

                                            char[] tempoAtualCaracteres = tempoAtual.toCharArray();

                                            if (tempoEntrega.length() != 23){

                                                while (tempoEntrega.length() != 23){
                                                    tempoEntrega += 0;
                                                }
                                            }
                                            char[] tempoEntregaCaracteres = tempoEntrega.toCharArray();

                                            tempoEntrega = String.valueOf(tempoEntregaCaracteres);
                                            tempoAtual = String.valueOf(tempoAtualCaracteres);

                                            Toast.makeText(TodoList.this, tempoEntrega, Toast.LENGTH_SHORT).show();
                                        }
                                    }, year, month, day);
                            datePickerDialog.show();
                         }
                    });

                    btnNewTask = v.findViewById(R.id.btnNewTask);

                    btnNewTask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            edtNewTask = v.findViewById(R.id.edtNewTask);

                            if(edtNewTask.getText().length() != 0){
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
                                Date dataAtual = null;
                                Date dataEntrega = null;

                                // arrumar -> passar esse tipo de data p/ a API
                                try {
                                    dataAtual = df.parse(tempoAtual);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    dataEntrega = df.parse(tempoEntrega);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                String anotation = edtNewTask.getText().toString();
                                Anotacao anotacao = new Anotacao(anotation, dataAtual, dataEntrega, tokenRecebido);
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

                                //Toast.makeText(TodoList.this, anotation + " " + dataAtual + " " + dataEntrega + " " + tokenRecebido, Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                            else
                                Toast.makeText(TodoList.this, "Coloque corretamente as informações!", Toast.LENGTH_SHORT).show();
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
}