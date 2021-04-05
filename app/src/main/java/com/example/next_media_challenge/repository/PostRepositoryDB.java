package com.example.next_media_challenge.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.util.room.PostDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class PostRepositoryDB {
    private PostDao mPostDao;
    private LiveData<List<PostModel>> getAllPost;
    private PostModel singlePost;
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
     *  get  Post by ID from DB
     *
     * @return
     */

    public  PostModel getPostById(int postId) throws ExecutionException, InterruptedException {
               return new getPostByIdAsyncTask(mPostDao).execute(postId).get();
    }

    public  void setPostById(PostModel post){

        this.singlePost = post;

    }


    /**
     * this a thread fro insterting data post to local database from specifical thread
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



    /**
     * this a thread fro delete data post to local database from specifical thread
     * we don't need it here
     */
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



    private  class getPostByIdAsyncTask extends AsyncTask<Integer,Void,PostModel>{
        private PostDao _postDao;
        private  PostModel _post;
        getPostByIdAsyncTask(PostDao postDao){
            _postDao=postDao;
        }


        @Override
        protected PostModel doInBackground(Integer... postId) {
            Log.v("idPost",postId[0]+" from doInBackground");
            Log.v("idPost",_postDao.getPostByID(postId[0]).getTitle()+" from doInBackground");

            _post= _postDao.getPostByID(postId[0]);
            Log.v("idPost",_post.getTitle()+" from doInBackground");            setPostById(_post);

            return _post;
        }
    }




}
