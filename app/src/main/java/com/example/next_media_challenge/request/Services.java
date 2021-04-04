package com.example.next_media_challenge.request;

import com.example.next_media_challenge.util.api.Constants;
import com.example.next_media_challenge.util.api.PostApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {
    /**
     * this class has Retrofit instase to use it in Repository class
     */
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    final private static PostApi postApi =retrofit.create(PostApi.class);



    public static PostApi getPostApi() {
        return postApi;
    }

}
