package com.example.next_media_challenge.model;

import com.google.gson.annotations.SerializedName;


public class PostModel  {

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

    /* @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;


    public int getDate() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    @Override
    public String toString() {
        return "PostModel{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }*/
}


/*
public class PostModel  {
    // Model Class for our Posts .
    @SerializedName("date")
    private String date;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private Title title;


    // Contractors
    public PostModel( String date, int id) {
        this.date = date;
        this.id = id;
    }



    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "PostModel{" +

                ", date='" + date + '\'' +
                ", id=" + id +
                '}';
    }

}
*/

