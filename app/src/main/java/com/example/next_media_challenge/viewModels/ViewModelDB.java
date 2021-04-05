package com.example.next_media_challenge.viewModels;

import android.app.Application;

import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.repository.PostRepositoryDB;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModelDB extends AndroidViewModel {

    private PostRepositoryDB mRepository;
    private LiveData<List<PostModel>> mAllPosts;

    public ViewModelDB(@NonNull Application application) {
        super(application);
        mRepository = new PostRepositoryDB(application);
        mAllPosts= mRepository.getAllPost();
    }

    public void insert(PostModel postModel){
        mRepository.insert(postModel);
    }

    public void delete(PostModel postModel){
        mRepository.delete(postModel);
    }

    public LiveData<List<PostModel>> getAllPosts() {
        return mAllPosts;
    }
}
