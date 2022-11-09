package com.example.applilyplanning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Imagem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload extends AppCompatActivity {

    AppCompatButton btnPlus;
    ImageButton ibtnTodoList, ibtnMaterias;
    GridView image;
    List<Imagem> itemsList = new ArrayList<>();
    int SELECT_PICTURE = 200;

    public static final int GET_FROM_GALLERY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        Integer tokenRecebido = params.getInt("token");

        btnPlus = findViewById(R.id.btnAdicionar);
        image = findViewById(R.id.gvFotos);
        ibtnTodoList = findViewById(R.id.ibtnTodoListPage);
        ibtnMaterias = findViewById(R.id.ibtnMaterias);

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<List<Imagem>> call = service.getImagens();

        call.enqueue(new Callback<List<Imagem>>() {
            @Override
            public void onResponse(Call<List<Imagem>> call, Response<List<Imagem>> response) {
                if (response.isSuccessful()){
                    int cont = 0;

                    if (response.body() != null){
                        while (cont < response.body().size()){
                            Imagem img = new Imagem(Uri.parse(response.body().get(cont).getImagem()));
                            itemsList.add(img);
                            cont++;
                        }
                        GridViewAdaptador customAdapter = new GridViewAdaptador(Upload.this, R.layout.upload, itemsList);
                        image.setAdapter(customAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Imagem>> call, Throwable t) {
                Toast.makeText(Upload.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        ibtnMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Upload.this, Materias.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        ibtnTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Upload.this, TodoList.class);
                Bundle params = new Bundle();

                params.putInt("token", tokenRecebido);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {

                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);

                    String url = "";

                    Bitmap bitmap= null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // initialize byte stream
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    // compress Bitmap
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    // Initialize byte array
                    byte[] bytes=stream.toByteArray();
                    // get base64 encoded string
                    url = String.valueOf(Base64.getEncoder().encode(bytes));

                    Imagem img = new Imagem(Uri.parse(url));
                    Call<Imagem> call = service.postImagem(img);

                    call.enqueue(new Callback<Imagem>() {
                        @Override
                        public void onResponse(Call<Imagem> call, Response<Imagem> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(Upload.this, "Imagem postada!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Imagem> call, Throwable t) {
                            Toast.makeText(Upload.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                    itemsList.add(img);
                    GridViewAdaptador customAdapter = new GridViewAdaptador(this, R.layout.upload, itemsList);
                    image.setAdapter(customAdapter);
                }
            }
        }
    }
}
