package com.example.applilyplanning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applilyplanning.model.Anotacao;

import java.util.List;

public class HojeAdaptador extends RecyclerView.Adapter<HojeAdaptador.ViewHolder>{

    private final List<Anotacao> todoList;

    public HojeAdaptador(List<Anotacao> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public HojeAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_today_alert, parent, false);
        return new HojeAdaptador.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView alert;

        public ViewHolder(View view){
            super(view);
            alert = view.findViewById(R.id.tvAlert);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HojeAdaptador.ViewHolder holder, int position) {
        final Anotacao toDoData = todoList.get(position);

        holder.alert.setText(toDoData.getAnotacao());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
