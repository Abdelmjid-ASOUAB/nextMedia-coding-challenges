package com.example.next_media_challenge.model;

import com.google.gson.annotations.SerializedName;


public class PostTitle {
    @SerializedName("rendered")
    private String rendered;

    // Contractors
    public PostTitle(String rendered) {
        this.rendered = rendered;
    }

    // Getter
    public String getRendered() {
        return rendered;
    }

    @Override
    public String toString() {
        return "Title{" +
                "rendered='" + rendered + '\'' +
                '}';
    }
}


