package com.example.applilyplanning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;
import java.util.List;

public class GridViewAdaptador extends BaseAdapter {
    private List<ImageView> listaDog;
    Context context;

    public GridViewAdaptador(Context context, List<ImageView> parametroList){
        this.listaDog = parametroList;
        this.context = context;
    }

    // retorna tamanho da lista
    @Override
    public int getCount() {
        return this.listaDog.size();
    }

    // pegar determinado item, pelo índice 'pos'
    @Override
    public Object getItem(int pos) {
        return this.listaDog.get(pos);
    }

    // retornar id do objeto
    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.upload, viewGroup, false);

        GridView imgDog = view.findViewById(R.id.gvFotos);

        return view;
    }
}
