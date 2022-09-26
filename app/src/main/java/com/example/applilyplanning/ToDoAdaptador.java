package com.example.applilyplanning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applilyplanning.model.ToDoModel;

import java.util.List;

class ToDoAdaptador extends RecyclerView.Adapter<ToDoAdaptador.ViewHolder> {

    private List<ToDoModel> todoList;
    private TodoList activity;

    public ToDoAdaptador(TodoList tdList){
        this.activity = tdList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_todo_list, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = (CheckBox) view.findViewById(R.id.chkToDo);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdaptador.ViewHolder holder, int position) {
        final ToDoModel item = todoList.get(position);

        holder.task.setText("item.getNomeLista()");
        //holder.task.setText(item.getIdAnotacao());
        //holder.task.setText(item.getNomeLista());
       // holder.task.setChecked(toBoolean(item.getStatus()));
    }

    private boolean toBoolean(int n){
        return n != 0;
    }

    public void setTasks(List<ToDoModel> lista){
        this.todoList = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
