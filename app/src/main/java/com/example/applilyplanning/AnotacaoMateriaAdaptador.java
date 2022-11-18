package com.example.applilyplanning;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applilyplanning.model.AnotacaoMateria;

import java.util.List;

public class AnotacaoMateriaAdaptador extends RecyclerView.Adapter<AnotacaoMateriaAdaptador.ViewHolder> {
    private List<AnotacaoMateria> anotacaoMateriaList;
    ItemClickListener itemClickListener;

    public AnotacaoMateriaAdaptador(List<AnotacaoMateria> anotacaoMateriaList){
        this.anotacaoMateriaList = anotacaoMateriaList;
    }

    @NonNull
    @Override
    public AnotacaoMateriaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.anotacao_materia_cell, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView data;
        Button excluir;

        public ViewHolder(View view){
            super(view);
            titulo = view.findViewById(R.id.tvTittle);
            data = view.findViewById(R.id.tvDate);
            excluir = view.findViewById(R.id.btnExcluirAnotacaoMateria);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position){
        final AnotacaoMateria item = anotacaoMateriaList.get(position);

        holder.titulo.setText(item.getTituloAnotacaoMateria());//precisa pegar do model, porém, ele quer da classe java????
        holder.data.setText(item.getDataAnotacaoMateria().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                itemClickListener.onItemClick(position, item);
            }
        });
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void setNotes(List<AnotacaoMateria> lista){
        this.anotacaoMateriaList = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return anotacaoMateriaList.size();
    }
}
