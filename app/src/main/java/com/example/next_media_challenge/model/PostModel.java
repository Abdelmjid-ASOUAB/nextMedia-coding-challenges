package com.example.next_media_challenge.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostModel  {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("date")
    private String date;

    @SerializedName("type")
    private String type;

    @SerializedName("link")
    private String link;

    @SerializedName("title")
    private PostTitle title;

    //Contractor
    public PostModel(int id, String date, String type, String link, PostTitle title) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.link = link;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public PostTitle getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title.toString() + '\'' +
                '}';
    }
}

