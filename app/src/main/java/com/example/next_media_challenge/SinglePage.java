package com.example.next_media_challenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.repository.PostRepositoryDB;
import com.example.next_media_challenge.viewModels.ViewModelDB;

public class SinglePage extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.next_media_challenge.extra_id";
    TextView _titleTextView;
    ViewModelDB _viewModelDB;
    PostModel _post;
    int _postId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page);
        initViewModelDB();

        initTitleTextView();
        getDataExtractData();
        getPostById(_postId);


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
        _postId =intent.getIntExtra(EXTRA_ID,-1);
        System.out.println(_postId);
        _titleTextView.setText(_postId+"");

    }


    private void initViewModelDB(){
        _viewModelDB = ViewModelProviders.of(this).get(ViewModelDB.class);

    }

    /**
     * get Post from Local data base by ID
     * @param id
     */
    private  void getPostById(int id){
        try {
            _post= _viewModelDB.getPostById(id);
            _titleTextView.setText(_post.getTitle().toString());
        }catch (Exception e){
            System.out.println(" eeeeeeeeeee  error eeeeeeeeee");
            System.out.println(e);
        }



    }
}