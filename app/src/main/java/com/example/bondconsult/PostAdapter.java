package com.example.bondconsult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHodler>  {

    private List<Post> myPostList;

    static class ViewHodler extends RecyclerView.ViewHolder{
        View postView;
        ImageView posterAvatar;
        TextView posterName;
        TextView postText;
        ImageView postImage;
        TextView postTime;
        public ViewHodler(View view)
        {
            super(view);
            postView=view;
            posterAvatar=(ImageView)view.findViewById(R.id.poster_avatar);
            posterName=(TextView)view.findViewById(R.id.poster_name);
            postText=(TextView)view.findViewById(R.id.post_text);
            postImage=(ImageView)view.findViewById(R.id.post_image);
            postTime=(TextView)view.findViewById(R.id.poster_time);
        }
    }
    public PostAdapter(List<Post> postList){
        myPostList=postList;
    }

    @Override
    public PostAdapter.ViewHodler onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post,parent,false);
        final PostAdapter.ViewHodler hodler=new PostAdapter.ViewHodler(view);
        hodler.posterAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击头像跳转到用户主页
                Toast.makeText(v.getContext(),"User",Toast.LENGTH_SHORT).show();
            }
        });
        hodler.posterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击用户名跳转到用户主页
                Toast.makeText(v.getContext(),"User",Toast.LENGTH_SHORT).show();
            }
        });
        hodler.postView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击帖子进入
                Toast.makeText(v.getContext(),"Post",Toast.LENGTH_SHORT).show();
                Activity currentActivity = (Activity) v.getContext();
                Intent intent = new Intent(currentActivity ,PostActivity.class);
                currentActivity.startActivity(intent);
            }
        });

        return hodler;
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHodler holder, final int position)
    {
        Post post=myPostList.get(position);
        holder.postImage.setImageBitmap(post.getPost_image());
        holder.postText.setText(post.getPost_text());
        holder.posterName.setText(post.getPoster_name());
        holder.posterAvatar.setImageBitmap(post.getPoster_avatar());
        holder.postTime.setText(post.getPost_time());

    }

    @Override
    public int getItemCount()
    {
        return myPostList.size();
    }
}
