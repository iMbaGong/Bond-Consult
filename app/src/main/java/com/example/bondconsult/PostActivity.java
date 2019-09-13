package com.example.bondconsult;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView commentsView;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList=new ArrayList<>();

    private TextView thumbupNum;
    private TextView forwardNum;
    private TextView commentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post) ;

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        ImageView postAvatar=(ImageView)findViewById(R.id.poster_avatar);
        TextView posterName=(TextView)findViewById(R.id.poster_name);
        TextView postTime=(TextView)findViewById(R.id.poster_time);
        TextView postText=(TextView)findViewById(R.id.post_text);
        ImageView postImage=(ImageView)findViewById(R.id.post_image);

        //TODO：设置帖子内容
        postAvatar.setImageResource(R.drawable.avatar);
        posterName.setText("Name");
        postTime.setText("2019-09-01");
        postText.setText("Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                "Hello!Hello!Hello!Hello!Hello!Hello!");
        postImage.setImageResource(R.drawable.totoro);


        commentsView=(RecyclerView)findViewById(R.id.comment_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        commentsView.setLayoutManager(layoutManager);
        commentAdapter=new CommentAdapter(commentList);
        commentsView.setAdapter(commentAdapter);

        Button forwardPost=(Button)findViewById(R.id.forward_post);
        Button commentPost=(Button)findViewById(R.id.comment_post);
        Button thumbupPost=(Button)findViewById(R.id.thumbup_post);

        forwardPost.setOnClickListener(this);
        commentPost.setOnClickListener(this);
        thumbupPost.setOnClickListener(this);

        thumbupNum=(TextView)findViewById(R.id.thumbup_num);
        forwardNum=(TextView)findViewById(R.id.forward_num);
        commentNum=(TextView)findViewById(R.id.comment_num);

    }

    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.forward_post:{
                // TODO: 转发帖子
                int num=Integer.parseInt(forwardNum.getText().toString());
                num++;
                forwardNum.setText(String.valueOf(num));
            }
                break;
            case R.id.comment_post:{
                // TODO: 评论帖子
                Intent intent=new Intent(PostActivity.this,CommentPostActivity.class);
                startActivityForResult(intent,1);
            }
                break;
            case R.id.thumbup_post: {
                // TODO: 点赞帖子
                int num=Integer.parseInt(thumbupNum.getText().toString());
                num++;
                thumbupNum.setText(String.valueOf(num));
            }
                break;
                default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String commentText=data.getStringExtra("comment_text");
                    Bitmap avatar= BitmapFactory.decodeResource(getResources(),R.drawable.avatar);
                    String name="name";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = new Date(System.currentTimeMillis());
                    String time=simpleDateFormat.format(date);

                    addPost(avatar,name,commentText,time);
                    commentNum.setText(String.valueOf(commentList.size()));
                }
                break;
                default:
        }
    }

    public void addPost(Bitmap comment_avatar, String comment_name, String comment_content,  String comment_time)
    {
//        Comment comment=new Comment(comment_avatar,comment_name,comment_content,comment_time);
        Comment comment=new Comment(new User(),comment_content,comment_time);
        commentList.add(comment);
        commentAdapter.notifyItemInserted(commentList.size()-1);
    }
}
