package com.example.next_media_challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.next_media_challenge.adapters.RecyclerViewCategoryAdapters;
import com.example.next_media_challenge.adapters.RecyclerViewPostAdapters;
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

    private RecyclerView _recyclerViewPosts;
    private RecyclerViewPostAdapters _adaptersPosts;
    private LinearLayoutManager _linearLayoutManagerPosts;


    private RecyclerView _recyclerViewCategory;
    private RecyclerViewCategoryAdapters _adaptersCategory;
    private LinearLayoutManager _linearLayoutCategory;

    private boolean loading = true;
    int lastVisibleItems, visibleItemCount, lastPart = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRepository();
        initViewModel();
        initRecyclerViewPost();
        initRecyclerViewCategory();
        loadPostsApi(page);


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
                    _postRepository.insert(post);
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.v("resp", "code error : " + t.getMessage());

            }
        });
    }


    /**
     * init Post repository
     **/
    private void initRepository() {
        _postRepository = new PostRepositoryDB(this.getApplication());
    }


    /**
     * method for init  RecyclerView  Posts content configuration & Listener
     */
    private void initRecyclerViewPost() {
        /**
         *  layout Manager for Posts
         */
        _linearLayoutManagerPosts = new LinearLayoutManager(this);

        /**
         *  recyclerView
         */
        _recyclerViewPosts = findViewById(R.id.post_recycler_view);
        _recyclerViewPosts.setLayoutManager(_linearLayoutManagerPosts);
        _recyclerViewPosts.setHasFixedSize(true);

        /**
         * Connect recycler View with adapter
         */
        _adaptersPosts = new RecyclerViewPostAdapters();
        _recyclerViewPosts.setAdapter(_adaptersPosts);

        /**
         *  bottom ScrollListener to Load more Posts on the bottom
         * */
        _recyclerViewPosts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                loadMorePostByPage(dy);
            }
        });


        _adaptersPosts.OnPostClickListener(new RecyclerViewPostAdapters.OnPostClickListener() {
            @Override
            public void OnPostClick(int idPost) {
                Intent  intent = new Intent(MainActivity.this,SinglePage.class);
                intent.putExtra(SinglePage.EXTRA_ID,idPost);
                startActivity(intent);
            }
        });



    }

    /**
     * method for init  RecyclerView  Posts content configuration
     */
    private void initRecyclerViewCategory() {

        /**
         *  layout Manager for Posts
         */
        _linearLayoutCategory = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);

        /**
         *  recyclerView
         */
        _recyclerViewCategory = findViewById(R.id.category_list);
        _recyclerViewCategory.setLayoutManager(_linearLayoutCategory);
        _recyclerViewCategory.setHasFixedSize(true);

        /**
         * Connect recycler View with adapter
         */
        _adaptersCategory = new RecyclerViewCategoryAdapters();
        _recyclerViewCategory.setAdapter(_adaptersCategory);

    }
    /**
     * method for load Post from APi using {loadPostsApi} on top
     * in this method i create a algorithm that check is there any new Post data
     * in Api i called {loadPostsApi} in OnCreate Method and  every scrolling 7 Posts
     * with this algorithm i keep local database updated with Api
     */
    private void loadMorePostByPage(int dy) {

        if (dy > 0) { //check for scroll down
            visibleItemCount = _linearLayoutManagerPosts.getChildCount();
            lastVisibleItems = _linearLayoutManagerPosts.findFirstVisibleItemPosition();

            if (lastVisibleItems != 0 && lastVisibleItems % 7 == 0 && lastPart != lastVisibleItems) {
                print("send update ====> " + lastVisibleItems + "  page : " + (++page));
                loadPostsApi(++page);
                lastPart = lastVisibleItems;
            }


        }
    }

    /**
     * init View Model and
     * get all data from database and passe them to post adapter and
     * add listener to listen if {loadPostData} add posts to  local DB
     */
    private void initViewModel(){
        viewModelDB = ViewModelProviders.of(this).get(ViewModelDB.class);
        viewModelDB.getAllPosts().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                _adaptersPosts.setPosts(postModels);
            }
        });

    }


    /**
     * init  Gradient app Bar
     * to Main Activity
     */
    private void appBarConfig() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ;
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