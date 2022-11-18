package com.example.applilyplanning;

import static android.util.Base64.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Imagem;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import android.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload extends AppCompatActivity {

    AppCompatButton btnPlus;
    ImageButton ibtnTodoList, ibtnMaterias;
    GridView image;
    FrameLayout home;
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
        home = findViewById(R.id.iconMenu);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Upload.this, Calendario.class);
                        Bundle params = new Bundle();

                        params.putInt("token", tokenRecebido);
                        //params.putString("key_user", user);
                        intent.putExtras(params);

                        startActivity(intent);
                    }
                });
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

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        Call<List<Imagem>> call = service.getImagens();

        call.enqueue(new Callback<List<Imagem>>() {
            @Override
            public void onResponse(Call<List<Imagem>> call, Response<List<Imagem>> response) {
                if (response.isSuccessful()){
                    int cont = 0;

                    if (response.body() != null){
                        while (cont < response.body().size()){
                            Imagem img = new Imagem(Uri.parse(response.body().get(cont).getImagem()), response.body().get(cont).getId());
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

        image.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Imagem img = (Imagem) image.getAdapter().getItem(position);

                Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                Call<Imagem> call = service.excluirImagem(img.getId());

                call.enqueue(new Callback<Imagem>() {
                    @Override
                    public void onResponse(Call<Imagem> call, Response<Imagem> response) {
                        Toast.makeText(Upload.this, "Imagem excluída!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Imagem> call, Throwable t) {
                        Toast.makeText(Upload.this, "Não foi possível excluir essa imagem!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    @SuppressLint("ResourceType")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                String url = "";

                    try {
                        Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                        byte[] bytes=stream.toByteArray();
                        url = Base64.encodeToString(bytes, Base64.DEFAULT);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    url = "data:image/png;base64," + url;
                    Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);

                    Imagem img = new Imagem(url);
                    Call<Imagem> call = service.postImagem(img);

                    call.enqueue(new Callback<Imagem>() {
                        @Override
                        public void onResponse(Call<Imagem> call, Response<Imagem> response) {
                            if (response.isSuccessful()){
                                Imagem imgNova = new Imagem(response.body().getImagem());
                                itemsList.add(imgNova);

                                Toast.makeText(Upload.this, "Imagem postada!", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(Upload.this, "Imagem muito grande!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Imagem> call, Throwable t) {
                            Toast.makeText(Upload.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    GridViewAdaptador customAdapter = new GridViewAdaptador(this, R.layout.upload, itemsList);
                    image.setAdapter(customAdapter);
            }
        }
    }
}
