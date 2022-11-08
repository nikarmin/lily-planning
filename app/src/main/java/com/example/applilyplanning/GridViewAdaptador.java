package com.example.applilyplanning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applilyplanning.model.Imagem;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GridViewAdaptador extends ArrayAdapter<Imagem> {
    List<Imagem> items_list = new ArrayList<>();
    Context context;

    public GridViewAdaptador(@NonNull Context context, int resource, @NonNull List<Imagem> objects){
        super(context, resource, objects);
        this.items_list = objects;
        this.context = context;
    }

    // retorna tamanho da lista
    @Override
    public int getCount() {
        return this.items_list.size();
    }

    // retornar id do objeto
    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {

            // getting reference to the main layout and
            // initializing
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.upload, null);
        }

        // initializing the imageview and textview
        // and setting data
        ImageView imageView = v.findViewById(R.id.ivFotos);

        // get the item using the  position param
        Imagem item = items_list.get(pos);

        imageView.setImageURI(Uri.parse(item.getImagem()));
        return v;
    }
}
