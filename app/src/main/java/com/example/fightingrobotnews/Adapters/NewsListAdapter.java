package com.example.fightingrobotnews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fightingrobotnews.Activitys.ArticlePageActivity;
import com.example.fightingrobotnews.Obj.NewsObj;
import com.example.fightingrobotnews.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsListAdapter extends BaseAdapter {

    private Context context;
    private List<NewsObj> items;
    private String gameName;

    public NewsListAdapter(Context context, List<NewsObj> items, String gameName){
        this.context = context;
        this.items = items;
        this.gameName = gameName;
    }

    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Object getItem(int i) {
        return items.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewsObj item = items.get(i);
        view = LayoutInflater.from(context).inflate(R.layout.article_item, null, false);
        ImageView ImageArticle = (ImageView) view.findViewById(R.id.ImageArticle);
        Picasso.get().load(item.getImg()).into(ImageArticle);
        TextView NameArticle = (TextView) view.findViewById(R.id.NameArticle);
        NameArticle.setText(item.getTitle());
        TextView DescriptionArticle = (TextView) view.findViewById(R.id.DescriptionArticle);
        DescriptionArticle.setText(item.getDescription());
        TextView DateArticle = (TextView) view.findViewById(R.id.DateArticle);
        DateArticle.setText("\uD83D\uDCC5 "+item.getDate());

        View.OnClickListener GoToNews = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticlePageActivity.class);
                intent.putExtra("game", gameName);
                intent.putExtra("data", item.toString());
                context.startActivity(intent);
            }
        };
        view.setOnClickListener(GoToNews);
        return view;
    }
}