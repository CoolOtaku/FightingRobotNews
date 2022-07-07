package com.example.fightingrobotnews.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.example.fightingrobotnews.Adapters.NewsListAdapter;
import com.example.fightingrobotnews.Obj.AppAPI;
import com.example.fightingrobotnews.Obj.Consts;
import com.example.fightingrobotnews.Obj.Helper;
import com.example.fightingrobotnews.Obj.NetWork;
import com.example.fightingrobotnews.Obj.NewsObj;
import com.example.fightingrobotnews.R;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView NewsListView;
    private EditText editSearch;
    private ImageButton Btn_Search;

    public static SharedPreferences sp;
    private String game;
    private List<NewsObj> NewsList;

    private Animation animClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        NewsListView = (ListView) findViewById(R.id.NewsListView);
        editSearch = (EditText) findViewById(R.id.editSearch);
        Btn_Search = (ImageButton) findViewById(R.id.Btn_Search);

        Intent localIntent = getIntent();
        game = localIntent.getStringExtra("game");

        if(NetWork.isConnection(NewsActivity.this)) {
            getNewsList(game);
        }else{
            NewsList = Helper.getDataInStorage(NewsActivity.this, game);
            if(NewsList.isEmpty()){
                Toast.makeText(NewsActivity.this, getString(R.string.sorry_not_exist_data), Toast.LENGTH_SHORT).show();
            }else{
                SetDataInList(NewsList, game);
            }
        }

        Btn_Search.setOnClickListener(this);
        animClick = AnimationUtils.loadAnimation(this, R.anim.btn_click1);
    }
    private void getNewsList(String game) {
        Call<List<NewsObj>> call = AppAPI.create().getNewsListData(Consts.API_KEY, "byGame", game);
        call.enqueue(new Callback<List<NewsObj>>() {
            @Override
            public void onResponse(Call<List<NewsObj>> call, Response<List<NewsObj>> response) {
                NewsList = response.body();
                SetDataInList(NewsList, game);
                Helper.SaveDataInStorage(game, NewsList);
            }
            @Override
            public void onFailure(Call<List<NewsObj>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(animClick);
        switch (view.getId()){
            case R.id.Btn_Search:
                SearchNews();
                break;
        }
    }
    private void SetDataInList(List<NewsObj> NewsList, String game){
        NewsListAdapter adapter = new NewsListAdapter(NewsActivity.this, NewsList, game);
        NewsListView.setAdapter(adapter);
    }

    private void SearchNews(){
        List<NewsObj> searchList = new ArrayList<>();
        String searchText = editSearch.getText().toString();
        for(NewsObj news : NewsList){
            if(!searchText.isEmpty()){
                String data = news.toString();
                if(data.contains(searchText) || data.contains(searchText.toUpperCase()) ||
                        data.contains(searchText.toLowerCase())){
                    searchList.add(news);
                }
            }else{
                searchList.add(news);
            }
        }
        SetDataInList(searchList, game);

    }
}