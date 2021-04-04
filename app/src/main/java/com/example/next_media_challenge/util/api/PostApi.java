package com.example.next_media_challenge.util.api;


import com.example.next_media_challenge.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * this class has methods for get data with Retrofit
 * for now i create just get post method
 */
public interface PostApi {
    /**
     * Getting Posts from Api by page
     * @param page
     *
     */
    @GET("wp/v2/posts")
    Call <List<PostModel>> getPosts(
            @Query("page") String  page
    );


}
