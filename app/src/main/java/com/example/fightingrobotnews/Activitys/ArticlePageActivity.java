package com.example.fightingrobotnews.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fightingrobotnews.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class ArticlePageActivity extends AppCompatActivity {

    private TextView GameTitle;
    private ImageView ArticleImage;
    private TextView ArticleTitle;
    private TextView ArticleDescription;
    private TextView ArticleText;
    private TextView ArticleDate;

    private Animation animClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_page);

        GameTitle = (TextView) findViewById(R.id.GameTitle);
        ArticleImage = (ImageView) findViewById(R.id.ArticleImage);
        ArticleTitle = (TextView) findViewById(R.id.ArticleTitle);
        ArticleDescription = (TextView) findViewById(R.id.ArticleDescription);
        ArticleText = (TextView) findViewById(R.id.ArticleText);
        ArticleDate = (TextView) findViewById(R.id.ArticleDate);

        Intent intent = getIntent();
        String dataJson = intent.getStringExtra("data");
        String gameName = intent.getStringExtra("game");

        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            Picasso.get().load(jsonObject.getString("img")).into(ArticleImage);
            ArticleTitle.setText(jsonObject.getString("title"));
            ArticleDescription.setText(jsonObject.getString("description"));
            ArticleText.setText(jsonObject.getString("text"));
            ArticleDate.setText("\uD83D\uDCC5 "+jsonObject.getString("date"));

            String gameFullName = gameName;
            switch(gameName){
                case "cs-go":
                    gameFullName = getString(R.string.counter_strike_global_offensive);
                    break;
                case "dota-2":
                    gameFullName = getString(R.string.dota_2);
                    break;
                case "lol":
                    gameFullName = getString(R.string.league_of_legends);
                    break;
                case "valorant":
                    gameFullName = getString(R.string.valorant);
                    break;
            }
            GameTitle.setText(gameFullName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        animClick = AnimationUtils.loadAnimation(this, R.anim.btn_click1);
    }

    public void GoToBack(View view) {
        view.startAnimation(animClick);
        super.onBackPressed();
    }
}