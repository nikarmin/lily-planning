package com.example.applilyplanning;

import com.example.applilyplanning.model.Anotacao;
import com.example.applilyplanning.model.AnotacaoMateria;
import com.example.applilyplanning.model.Materia;

public interface ItemClickListener {
    void onItemClick(int position, Anotacao userData);
    void onItemClick(int position, Materia userData);
    void onItemClick(int position, AnotacaoMateria item);
}
