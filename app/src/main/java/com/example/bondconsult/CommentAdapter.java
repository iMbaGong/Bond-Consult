package com.example.bondconsult;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHodler> {
    private List<Comment> myCommentList;

    static class ViewHodler extends RecyclerView.ViewHolder{
        ImageView commentAvatar;
        TextView commentName;
        TextView commentContent;
        TextView commentTime;
        public ViewHodler(View view)
        {
            super(view);
            commentAvatar=(ImageView)view.findViewById(R.id.comment_avatar);
            commentName=(TextView)view.findViewById(R.id.comment_name);
            commentContent=(TextView)view.findViewById(R.id.comment_content);
            commentTime=(TextView)view.findViewById(R.id.comment_time);
        }
    }
    public CommentAdapter(List<Comment> commentList){
        myCommentList=commentList;
    }

    @Override
    public CommentAdapter.ViewHodler onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment,parent,false);
        final CommentAdapter.ViewHodler hodler=new CommentAdapter.ViewHodler(view);
        hodler.commentAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击头像跳转到用户主页
                Toast.makeText(v.getContext(),"User",Toast.LENGTH_SHORT).show();
            }
        });
        hodler.commentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 点击用户名跳转到用户主页
                Toast.makeText(v.getContext(),"User",Toast.LENGTH_SHORT).show();
            }
        });

        return hodler;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHodler holder, final int position)
    {
        Comment comment=myCommentList.get(position);
        holder.commentAvatar.setImageBitmap(comment.getCommentAvatar());
        holder.commentName.setText(comment.getCommentName());
        holder.commentContent.setText(comment.getCommentContent());
        holder.commentTime.setText(comment.getCommentTime());
    }

    @Override
    public int getItemCount()
    {
        return myCommentList.size();
    }
}
