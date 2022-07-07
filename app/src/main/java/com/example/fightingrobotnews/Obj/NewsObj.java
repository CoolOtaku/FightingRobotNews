package com.example.fightingrobotnews.Obj;

import com.google.gson.annotations.SerializedName;

public class NewsObj {

    @SerializedName("title")
    private String title;
    @SerializedName("img")
    private String img;
    @SerializedName("description")
    private String description;
    @SerializedName("text")
    private String text;
    @SerializedName("date")
    private String date;

    public NewsObj(String title, String img, String description, String text, String date) {
        this.title = title;
        this.img = img;
        this.description = description;
        this.text = text;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{\n"+
                "\"title\":\"" + title + "\",\n" +
                "\"img\":\"" + img + "\",\n" +
                "\"description\":\"" + description +"\",\n"+
                "\"text\":\"" + text +"\",\n"+
                "\"date\":\"" + date +"\"\n"+
                "}";
    }

}
