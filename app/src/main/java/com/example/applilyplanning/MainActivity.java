package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView text;
    SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        text = findViewById(R.id.tvAtualData);

        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();

        Locale.setDefault(new Locale("pt", "BR"));

        String dayName = dow.getDisplayName(TextStyle.FULL, Locale.getDefault());
        // arrumar para a lang portugues
        text.setText( date.getMonth() + " " + dayName + ", " + dow.getValue() + " de " + date.getYear());
    }
}