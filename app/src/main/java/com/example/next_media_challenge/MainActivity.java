package com.example.next_media_challenge;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.repository.PostRepositoryDB;
import com.example.next_media_challenge.request.Services;
import com.example.next_media_challenge.util.api.PostApi;
import com.example.next_media_challenge.viewModels.ViewModelDB;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button _testButton;

    int page = 1;

    // database var
    ViewModelDB viewModelDB;
    private PostRepositoryDB _postRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTestButton();

    }

    /**
     * init Test Button
     */
    private void initTestButton(){
        _testButton =findViewById(R.id.button3);
        _testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPostsApi(1);
            }
        });
    }


    /***
     *
     *   method for load data posts form API by Page
     *   you passe
     *   @param page number and
     *   @return List<PostModel>  in the page
     *    and save them in local DB
     *
     */
    private void loadPostsApi(int page) {
        PostApi postApi = Services.getPostApi();
        Call<List<PostModel>> call = postApi.getPosts(page + "");
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<PostModel> posts = response.body();
                for (PostModel post : posts) {

                    /** insert post in Local Db **/
                    //TODO: insert post in Local Db
                    print(post.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.v("resp", "code error : " + t.getMessage());

            }
        });
    }





    /**
     * i use it for pint data
     * i use it  in place of others System ...... XD
     *
     * @param str
     */
    static void print(String str) {
        System.out.println(str);
    }


}