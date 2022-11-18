package com.example.applilyplanning;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applilyplanning.model.Materia;

import java.util.List;

public class MateriaAdaptador extends RecyclerView.Adapter<MateriaAdaptador.ViewHolder> {

    private List<Materia> materiaList;
    private Materias activity;
    ItemClickListener itemClickListener;

    public MateriaAdaptador(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @NonNull
    @Override
    public MateriaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        ImageView color;

        public ViewHolder(View view) {
            super(view);
            subject = view.findViewById(R.id.subjectTextView);
            color = view.findViewById(R.id.subjectImageView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaAdaptador.ViewHolder holder, @SuppressLint("RecyclerView") int position){
        final Materia item = materiaList.get(position);

        holder.subject.setText(item.getNomeMateria());
        holder.color.setBackgroundColor(Color.parseColor(item.getCorMateria()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(position, item);
            }
        });
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private boolean toBoolean(int n){
        return n != 0;
    }

    public void setSubjects(List<Materia> lista){
        this.materiaList = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return materiaList.size();
    }
}
