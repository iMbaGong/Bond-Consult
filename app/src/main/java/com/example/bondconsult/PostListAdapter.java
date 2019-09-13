package com.example.bondconsult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    public  List<Post> mPostList;
    private Context context;

    public PostListAdapter (List<Post> src){
        mPostList = src;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView postTitle;
        TextView postInfo;
        TextView posterInfo;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            postTitle = view.findViewById(R.id.post_title);
            postInfo = view.findViewById(R.id.post_info);
            posterInfo = view.findViewById(R.id.poster_info);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(context == null){
            context = viewGroup.getContext();
        }

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_post_list,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.posterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击头像跳转到用户主页
                Toast.makeText(context,"User",Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击帖子进入
                int position = holder.getAdapterPosition();
                Log.d("get the position", ":"+position);
                Intent intent = new Intent(context,PostActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.postTitle.setText(mPostList.get(i).getPost_title());
        viewHolder.postInfo.setText(mPostList.get(i).getPost_text());
        viewHolder.posterInfo.setText(mPostList.get(i).getPost_time()+"  "+mPostList.get(i).getPoster_name());
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }


}
