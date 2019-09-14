package com.example.bondconsult;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView commentsView;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private Post post;
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


        int position = getIntent().getIntExtra("position",-1);
        post = PostFragment.mPostList.get(position);
        if(post.getCommentList()==null)
            commentList = new ArrayList<>();
        else commentList = post.getCommentList();


        //TODO：设置帖子内容
        postAvatar.setImageBitmap(post.getPoster_avatar());//todo title
        posterName.setText(post.getPoster_name());
        postTime.setText(post.getPost_time());
        postText.setText(post.getPost_text());
        //postImage.setImageResource(R.drawable.totoro);
        //todo image

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
                    commentList.add(new Comment(mUtil.user,
                            commentText,
                            mUtil.getCurrentTime())
                    );
                    new upLoadTask().execute();
                    commentAdapter.notifyDataSetChanged();
                    commentNum.setText(String.valueOf(commentList.size()));
                    thumbupNum.setText(String.valueOf(post.getThumbUp()));
                    post.addCommNum(1);
                }
                break;
                default:
        }
    }


    public class upLoadTask extends AsyncTask<Void,Integer,Boolean>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(PostActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading data");
            progressDialog.setMessage("Loading");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20,TimeUnit.SECONDS)
                        .build();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                Gson gson = new Gson();
                builder.addFormDataPart("new_comment",gson.toJson(commentList.get(commentList.size()-1)));
                builder.addFormDataPart("time",post.getPost_time());
                Log.d("send:", post.getPost_time());
                Request request = new Request.Builder()
                        .url("http://108.61.223.76/add_comment.php")
                        .post(builder.build())
                        .build();
                Response response = client.newCall(request).execute();
                String resBody = response.body().string();
                Log.d("res for add comment", "length:"+resBody.substring(0,resBody.length()>20?20:resBody.length()));

                commentList.clear();

                commentList = gson.fromJson(resBody,new TypeToken<List<Comment>>(){}.getType());

                Log.d("parse", "listsize:"+commentList.size());

            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.dismiss();
            commentAdapter=new CommentAdapter(commentList);
            commentsView.setAdapter(commentAdapter);

        }

    }




}
