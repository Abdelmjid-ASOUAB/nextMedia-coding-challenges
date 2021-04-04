package com.example.next_media_challenge.request;

import android.content.Context;
import android.os.AsyncTask;

import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.util.room.Converters;
import com.example.next_media_challenge.util.room.PostDao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = PostModel.class,version = 1)
@TypeConverters(Converters.class)
public abstract class PostRoomDb extends RoomDatabase {
    private static PostRoomDb instance;
    public abstract PostDao postDao();

    //Singlton
    public static synchronized PostRoomDb getInstance(Context context){
        if(instance ==null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                PostRoomDb.class, "post-database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private PostDao mPostDao;

        public PopulateDataAsyncTask(PostRoomDb db) {
        mPostDao= db.postDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           return null;
        }
    }

}
