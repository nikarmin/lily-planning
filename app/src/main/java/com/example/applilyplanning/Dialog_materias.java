package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Dialog_materias extends AppCompatActivity {

    ImageView wheelColor;
    View showColor;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_materias);

        wheelColor = findViewById(R.id.colorPicker);
        showColor = findViewById(R.id.showColor);

        wheelColor.setDrawingCacheEnabled(true);
        wheelColor.buildDrawingCache(true);

        wheelColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = wheelColor.getDrawingCache();

                    try{
                        if ((int)motionEvent.getX() >= 0 || (int)motionEvent.getY() >= 0){
                            int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());

                            int r = Color.red(pixel);
                            int g = Color.green(pixel);
                            int b = Color.blue(pixel);

                            showColor.setBackgroundColor(Color.rgb(r,g,b));
                        }
                    }
                    catch (Exception error){
                        Toast.makeText(Dialog_materias.this, "Limite alcançado!", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }
}