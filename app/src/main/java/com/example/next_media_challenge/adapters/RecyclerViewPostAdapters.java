package com.example.next_media_challenge.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.next_media_challenge.R;
import com.example.next_media_challenge.model.PostModel;
import com.example.next_media_challenge.util.room.Converters;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewPostAdapters extends RecyclerView.Adapter<RecyclerViewPostAdapters.PostViewHolder> {
    private List<PostModel> _Posts=new ArrayList<>();
    private  OnPostClickListener _Listener;
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel currentPost=_Posts.get(position);
        holder.title.setText(currentPost.getTitle().getRendered());
        holder.date.setText(Converters.far.format(Converters.fromStringToDate(currentPost.getDate())));
        holder.id=currentPost.getId();
    }

    //Link  updated list [Post] with [_Posts]
    public void setPosts(List<PostModel> posts){
        _Posts =posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return _Posts == null ?0: _Posts.size();
    }

    public  class PostViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        int id;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            date= itemView.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    if(_Listener!=null && position != RecyclerView.NO_POSITION){
                        _Listener.OnPostClick(_Posts.get(position).getId());
                    }
                }
            });

        }
    }

    /**
     * add listener for the Post item
     */
    public interface OnPostClickListener{
        void OnPostClick(int idPost);
    }

    public void OnPostClickListener(OnPostClickListener listener){
        _Listener =listener;
    }

}
