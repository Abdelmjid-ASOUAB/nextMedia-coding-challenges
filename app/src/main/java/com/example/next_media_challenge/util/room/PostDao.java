package com.example.next_media_challenge.util.room;


import com.example.next_media_challenge.model.PostModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PostDao {
    @Insert
    void insert(PostModel post);

    @Delete
    void delete(PostModel post);

    @Query("DELETE from PostModel")
    void deleteAll();

    @Query("SELECT * from PostModel ORDER BY date DESC")
    LiveData<List<PostModel>> getAllPostFromDb();
}
