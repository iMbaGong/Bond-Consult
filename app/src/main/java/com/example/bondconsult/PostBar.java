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
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostBar extends AppCompatActivity {

    private RecyclerView postsView;
    private PostAdapter postAdapter;
    private List<Post> postList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postbar);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        ImageView backButton=(ImageView)findViewById(R.id.back_button);
        ImageView newPostButton=(ImageView)findViewById(R.id.new_post_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PostBar.this,NewPostActivity.class);
                startActivityForResult(intent,2);
            }
        });


        postsView=(RecyclerView)findViewById(R.id.posts_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        postsView.setLayoutManager(layoutManager);
        postAdapter=new PostAdapter(postList);
        postsView.setAdapter(postAdapter);

        initPost();

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 2:
                if(resultCode==RESULT_OK){
                    //发布新帖子
                    Toast.makeText(PostBar.this,"Success",Toast.LENGTH_SHORT).show();
                    String postText=data.getStringExtra("post_text");
                    byte[] bis = data.getByteArrayExtra("post_picture");
                    Bitmap postPicture = BitmapFactory.decodeByteArray(bis, 0, bis.length);

//                    Bitmap postPicture= BitmapFactory.decodeResource(getResources(),R.drawable.avatar);
                    Bitmap avatar = BitmapFactory.decodeResource(getResources(),R.drawable.totoro);
                    String name="name";

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = new Date(System.currentTimeMillis());
                    String time=simpleDateFormat.format(date);

                    addPost(avatar,name,postText,postPicture,time);
                }
                break;
            default:
        }
    }

    private void initPost()
    {
        Bitmap avatar = BitmapFactory.decodeResource(getResources(),R.drawable.avatar);
        Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.totoro);
        String name="name";
        String time="2019-09-01";
        String text="Hello!Hello!Hello!Hello!Hello!Hello!\n" +
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
                "Hello!Hello!Hello!Hello!Hello!Hello!";
        addPost(avatar,name,text,image,time);
    }

    public void addPost(Bitmap poster_avatar, String poster_name, String post_text, Bitmap post_image,String post_time)
    {
        Post post=new Post(poster_avatar,poster_name,post_text,post_image,post_time,"Hello");
        postList.add(post);
        postAdapter.notifyItemInserted(postList.size()-1);
    }
}
