package com.example.next_media_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SinglePage extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.next_media_challenge.extra_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page);



    }
}