package com.example.fightingrobotnews.Intarfaces;

import com.example.fightingrobotnews.Obj.NewsObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Server {
    @FormUrlEncoded
    @POST("getNews")
    Call<List<NewsObj>> getNewsListData (@Field("api_key") String api_key, @Field("type") String type, @Field("gameName") String gameName);
}
