package com.example.applilyplanning.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> listEvents = new ArrayList<>();

    private String tituloNota;
    private String dataNota;
    private String notaa;

    public Event(String tituloNota, String dataNota, String notaa){
        this.tituloNota = tituloNota;
        this.dataNota = dataNota;
        this.notaa = notaa;
    }

    public String getTituloNota(){
        return tituloNota;
    }

    public String getDataNota(){
        return dataNota;
    }

    public String getNotaa(){
        return notaa;
    }

    public void setTituloNota(){
        this.tituloNota = tituloNota;
    }

    public void setDataNota(){
        this.dataNota = dataNota;
    }

    public void setNotaa(){
        this.notaa = notaa;
    }
}