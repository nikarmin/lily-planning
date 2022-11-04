package com.example.applilyplanning;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applilyplanning.model.Materia;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Calendario extends AppCompatActivity {

    TextView text;
    LocalDate date = LocalDate.now();
    private Toolbar toolbar;
    Calendar myCalendar;
    ImageButton ibtnTodoListPage, ibtnMaterias;
    private SimpleDateFormat dateFormatForMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        ibtnTodoListPage = findViewById(R.id.ibtnTodoListPage);
        ibtnMaterias = findViewById(R.id.ibtnMaterias);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");

        ibtnMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendario.this, Materias.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        ibtnTodoListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Calendario.this, TodoList.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        Locale local = new Locale("pt", "BR");
        Locale.setDefault(local);
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy", local);
        DayOfWeek dow = date.getDayOfWeek();
        Date now = new Date();
        String dayName = dow.getDisplayName(TextStyle.NARROW, Locale.getDefault());

        text = findViewById(R.id.tvAtualData);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        String mes = dateFormat.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, local);

        String diaDaSemana = pesquisarDia();

        actionBar.setTitle(null);
        actionBar.setTitle(mes.toUpperCase());

        text.setText(diaDaSemana + ", " + now.getDate()+ " de " + mes + " de " + date.getYear());

        String[] testString = { "Seg", "Ter", "Qua", "Quin", "Sex", "Sáb", "Dom" };

        compactCalendarView.setLocale(TimeZone.getDefault(), local);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.setDayColumnNames(testString);
        dateFormatForMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Toast.makeText(Calendario.this, "Date : " + dateClicked.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth).toUpperCase());
            }
        });



       /*LocalDate date = LocalDate.now();
        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("MMMMM", local);
        DayOfWeek dow = date.getDayOfWeek();
        Calendar calendar = Calendar.getInstance();
        Locale.setDefault(new Locale("pt", "BR"));

        String dayName = dow.getDisplayName(TextStyle.FULL, Locale.getDefault());
        // arrumar para a lang portugues
        text.setText(dateFormat.format(calendar.getTime()) + " " + dayName + ", " + dow.getValue() + " de " + date.getYear());*/
    }

    private String pesquisarDia(){

        String diaDaSemana = "";
        Calendar myDate = Calendar.getInstance();

        switch (myDate.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY :
                diaDaSemana = "Domingo";
                break;
            case Calendar.MONDAY :
                diaDaSemana = "Segunda-feira";
                break;
            case Calendar.TUESDAY :
                diaDaSemana = "Terça-feira";
                break;
            case Calendar.WEDNESDAY :
                diaDaSemana = "Quarta-feira";
                break;
            case Calendar.THURSDAY :
                diaDaSemana = "Quinta-feira";
                break;
            case Calendar.FRIDAY :
                diaDaSemana = "Sexta-feira";
                break;
            case Calendar.SATURDAY :
                diaDaSemana = "Sábado";
                break;
        }
        return diaDaSemana;

    }
}