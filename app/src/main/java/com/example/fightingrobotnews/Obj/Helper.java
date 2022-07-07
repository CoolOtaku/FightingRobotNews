package com.example.fightingrobotnews.Obj;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fightingrobotnews.Activitys.NewsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static ArrayList<NewsObj> getDataInStorage(Context context, String dataName){
        Gson gson = new Gson();
        String jsonData = NewsActivity.sp.getString(dataName, "");
        if(jsonData.isEmpty()){
            return new ArrayList<NewsObj>();
        }
        Type type = new TypeToken<List<NewsObj>>(){}.getType();
        return gson.fromJson(jsonData, type);
    }

    public static void SaveDataInStorage(String game, List<NewsObj> NewsList){
        SharedPreferences.Editor editor = NewsActivity.sp.edit();
        Gson gson = new Gson();
        String jsonData = gson.toJson(NewsList);
        editor.putString(game, jsonData);
        editor.apply();
    }

}
