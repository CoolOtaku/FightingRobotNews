package com.example.fightingrobotnews.Obj;

import com.example.fightingrobotnews.Intarfaces.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppAPI {
    public static Server create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Server api = retrofit.create(Server.class);
        return api;
    }
}
