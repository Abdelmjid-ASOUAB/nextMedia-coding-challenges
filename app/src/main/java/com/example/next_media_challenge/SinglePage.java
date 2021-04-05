package com.example.next_media_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SinglePage extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.next_media_challenge.extra_id";
    TextView _titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page);

        initTitleTextView();
        getDataExtractData();


    }

    /**
     * init title TextView
     */
    private void initTitleTextView(){
        _titleTextView =findViewById(R.id.title_text);
    }

    /**
     * get EXTRACT  id Post and set it to Title TextView
     */
    private void getDataExtractData(){
        Intent intent =getIntent();
        _titleTextView.setText(intent.getIntExtra(EXTRA_ID,-1)+"");
    }
}