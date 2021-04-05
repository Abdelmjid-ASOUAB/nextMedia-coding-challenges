package com.example.next_media_challenge.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.util.room.PostDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PostRepositoryDB {
    private PostDao mPostDao;
    private LiveData<List<PostModel>> getAllPost;

    /** class Constructor
     * @param app
     */
    public PostRepositoryDB(Application app){
        PostRoomDb db = PostRoomDb.getInstance(app);
        mPostDao =db.postDao();
        getAllPost=mPostDao.getAllPostFromDb();
    }


    /**
     * for insert to DB
     *
     * @param post
     */
    public void insert(PostModel post){
        new InsertAsyncTask(mPostDao).execute(post);
    }

    /**
     * for  delete post from DB
     *
     * @param post
     */
    public void delete(PostModel post){
        new DeleteAsyncTask(mPostDao).execute(post);
    }

    /**
     *  Update Post  Coming Soon
     *
     * @param post
     */
    public void update(PostModel post){

    }

    /**
     *  get All Post from DB
     *
     * @return
     */
    public LiveData<List<PostModel>> getAllPost(){
        return  getAllPost;
    }



    /**
     * this a thread fro insterting data post to local database
     */
    private static class InsertAsyncTask extends AsyncTask<PostModel,Void,Void> {
        private PostDao _postDao;

        InsertAsyncTask(PostDao postDao){
            _postDao=postDao;
        }
        @Override
        protected Void doInBackground(PostModel... postModels) {
            try {

            _postDao.insert(postModels[0]);
            }catch (Exception e){
                System.out.println(postModels[0].getId()+" already Existed");
            }
            return null;
        }
    }



    private static class DeleteAsyncTask extends AsyncTask<PostModel,Void,Void>{
        private PostDao _postDao;

        DeleteAsyncTask(PostDao postDao){
            _postDao=postDao;
        }
        @Override
        protected Void doInBackground(PostModel... postModels) {
            _postDao.delete(postModels[0]);
            return null;
        }
    }


}
