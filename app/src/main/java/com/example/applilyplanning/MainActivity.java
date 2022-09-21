package com.example.applilyplanning;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

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


public class MainActivity extends AppCompatActivity {

    TextView text;
    LocalDate date = LocalDate.now();

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

        String mes = dateFormat.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, local);
        mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);

        String diaDaSemana = pesquisarDia();

        text.setText(diaDaSemana + ", " + Calendar.DATE + " de " + mes + " de " + date.getYear());

        Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
        compactCalendarView.addEvent(ev1);

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev2 = new Event(Color.GREEN, 1433704251000L);
        compactCalendarView.addEvent(ev2);

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
        List<Event> events = compactCalendarView.getEvents(1433701251000L); // can also take a Date object

        // events has size 2 with the 2 events inserted previously
        Log.d(TAG, "Events: " + events);

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
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