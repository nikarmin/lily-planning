package com.example.applilyplanning.database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig
{
    //143.106.202.81 5g
    //177.220.18.15
    //192.168.15.101 normal
    private static final String BASE_URL = "http://192.168.15.101:3030/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}