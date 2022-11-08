package com.example.applilyplanning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applilyplanning.model.Anotacao;

import java.util.List;

public class GridViewAnotacoesAdaptador extends BaseAdapter {
    private List<Anotacao> listaAnotacoes;
    Context context;

    public GridViewAnotacoesAdaptador(Context context, List<Anotacao> listaAnotacoes){
        this.listaAnotacoes = listaAnotacoes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.listaAnotacoes.size();
    }

    @Override
    public Object getItem(int pos) {
        return this.listaAnotacoes.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.gridview_anotacoes, viewGroup, false);

        TextView tvAnotacao = view.findViewById(R.id.tvAnotacao);

        final Anotacao anotacao = listaAnotacoes.get(pos);

        tvAnotacao.setText(anotacao.getAnotacao());

        return view;
    }
}