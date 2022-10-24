package com.example.applilyplanning;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applilyplanning.database.RetrofitConfig;
import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.Professor;
import com.example.applilyplanning.model.ToDo;

import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ToDoAdaptador extends RecyclerView.Adapter<ToDoAdaptador.ViewHolder> {

    //private List<ToDo> todoList;
    private List<Anotacao> todoList;
    private TodoList activity;
    ItemClickListener itemClickListener;

    public ToDoAdaptador(/*List<ToDo> tdList*/ List<Anotacao> todoList){
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public ToDoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        EditText edtNewTask;
        CheckBox toDoCheckBox;

        public ViewHolder(View view){
            super(view);
            //edtNewTask = view.findViewById(R.id.edtNewTask);
            toDoCheckBox = view.findViewById(R.id.chkToDo);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdaptador.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //final ToDo toDoData = todoList.get(position);

        final Anotacao toDoData = todoList.get(position);

        holder.toDoCheckBox.setText(toDoData.getAnotacao());

        /*holder.toDoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Anotacao anotacao = new Anotacao(toDoData.getAnotacao());
                Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
                Call<Anotacao> call = service.excluirAnotacao(anotacao.getIdAnotacao());

                call.enqueue(new Callback<Anotacao>() {
                    @Override
                    public void onResponse(Call<Anotacao> call, Response<Anotacao> response) {
                        if (response.isSuccessful()){
                            todoList.remove(position);
                        }
                    }

                    @Override
                    public void onFailure(Call<Anotacao> call, Throwable t) {
                        //Toast.makeText(TodoList.CONTEXT_RESTRICTED, "uiuiu", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        //holder.toDoCheckBox.setText(toDoData.getToDoLabel());
        //final ToDo item = todoList.get(position);
        ///holder.edtNewTask.setText(item.getNomeLista());
        //holder.task.setText(item.getIdAnotacao());
        //holder.task.setText(item.getNomeLista());
       // holder.task.setChecked(toBoolean(item.getStatus()));
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private boolean toBoolean(int n){
        return n != 0;
    }

    public void setTasks(/*List<ToDo> lista*/ List<Anotacao> lista){
        this.todoList = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
