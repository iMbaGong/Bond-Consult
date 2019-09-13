package com.example.bondconsult;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int ADD_PICTURE =1;

    private EditText editText;
    private RecyclerView picRecyclerView;
    private Adapter adapter;

    private List<myPicture> pictureList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        Button addPicture=(Button)findViewById(R.id.add_picture);
        Button exitButton=(Button)findViewById(R.id.exit_button);
        Button sendButton=(Button)findViewById(R.id.send_button);
        editText=(EditText)findViewById(R.id.edit_text);
        picRecyclerView=(RecyclerView) findViewById(R.id.recycler_view);


        addPicture.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        picRecyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(pictureList);
        picRecyclerView.setAdapter(adapter);

        //使输入框获得焦点
        editText.requestFocus();

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.add_picture:
            {
                if(ContextCompat.checkSelfPermission(NewPostActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(NewPostActivity.this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                else {
                    openAlbum();
                }
            }
            break;
            case R.id.exit_button:
                // TODO:  退出按钮
                finish();
                break;
            case R.id.send_button:
                //TODO: 发布按钮
            {
                if(!editText.getText().toString().isEmpty()&&
                        adapter.getItemCount()>0){
                    Intent intent=getIntent();
                    User user = (User) intent.getSerializableExtra("user");
                    Post post = new Post(
                            "title",//TODO title
                            mUtil.getCurrentTime(),
                            editText.getText().toString(),
                            user,
                            0,
                            0
                    );
                    intent.putExtra("post_text",editText.getText().toString());
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.totoro);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bitmapByte = baos.toByteArray();
                    intent.putExtra("post_picture", bitmapByte);

                    setResult(RESULT_OK,intent);
                }
                finish();
            }
                break;
                default:break;

        }
    }

    private void openAlbum()
    {
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,ADD_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,
                                           int[]grantResults)
    {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    openAlbum();
                }
                else {
                    Toast.makeText(NewPostActivity.this,"Permission denied.",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case ADD_PICTURE:
                if(resultCode==RESULT_OK)
                {
                    if(Build.VERSION.SDK_INT>=19)
                    {
                        handleImageOnKitKat(data);
                    }
                    else {
                        handleImageBeforeKitKat(data);
                    }

                }
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri))
        {
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority()))
            {
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority()))
            {
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection) {
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if(imagePath!=null)
        {
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);


            myPicture picture=new myPicture(bitmap);

            pictureList.add(pictureList.size(),picture);
            adapter.notifyItemInserted(pictureList.size()-1);
        }
        else {
            Toast.makeText(NewPostActivity.this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    private class UploadTask extends AsyncTask<Void,Integer,Boolean>{

        private ProgressDialog progressDialog;
        private boolean res = false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NewPostActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Upload data");
            progressDialog.setMessage("Uploading");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Intent intent=getIntent();
                User user = (User) intent.getSerializableExtra("user");
                String time = mUtil.getCurrentTime();
                Post post = new Post(
                        "title",//TODO title
                        time,
                        editText.getText().toString(),
                        user,
                        0,
                        0
                );
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(0, TimeUnit.SECONDS)
                        .readTimeout(0, TimeUnit.SECONDS)
                        .writeTimeout(0, TimeUnit.SECONDS)
                        .build();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                builder.addFormDataPart("title","title");//TODO title
                builder.addFormDataPart("user",new Gson().toJson(user));
                builder.addFormDataPart("text",editText.getText().toString());

                Request request = new Request.Builder()
                        .url("http://108.61.223.76/new_post.php")
                        .post(builder.build())
                        .build();
                Response response = client.newCall(request).execute();
                String strRes = response.body().string();
                JSONObject jsonObject = new JSONObject(strRes);
                if(jsonObject.getString("state").equals("success")){
                    res = true;
                    setResult(RESULT_OK,intent);
                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }



}
