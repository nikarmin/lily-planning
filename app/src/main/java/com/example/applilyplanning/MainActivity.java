package com.example.applilyplanning;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {

    TextView text;
    LocalDate date = LocalDate.now();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        //"EEE, MMM d, ''yy"

        Locale local = new Locale("pt", "BR");
        Locale.setDefault(local);
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy", local);
        DayOfWeek dow = date.getDayOfWeek();
        String dayName = dow.getDisplayName(TextStyle.NARROW, Locale.getDefault());

        text = findViewById(R.id.tvAtualData);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        String mes = dateFormat.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, local);
        mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);

        String diaDaSemana = pesquisarDia();

        actionBar.setTitle(null);
        actionBar.setTitle(mes);

        text.setText(diaDaSemana + ", " + Calendar.DATE + " de " + mes + " de " + date.getYear());

        compactCalendarView.setLocale(TimeZone.getDefault(), local);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

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