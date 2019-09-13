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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    public static final int MODIFY_PICTURE =1;

    private ImageView userAvatar;
    private Bitmap avatar;
    EditText usernameText;
    EditText passwordText;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        Button signupButton=(Button)findViewById(R.id.signup_button);
        usernameText=(EditText)findViewById(R.id.username_text);
        passwordText=(EditText)findViewById(R.id.password_text);
        final EditText repasswordText=(EditText)findViewById(R.id.repassword_text);
        userAvatar=(ImageView)findViewById(R.id.user_avatar);

        userAvatar.setImageResource(R.drawable.default_avatar);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(SignUpActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(SignUpActivity.this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                else {
                    openAlbum();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(usernameText.getText().toString().isEmpty()
                        ||passwordText.getText().toString().isEmpty()
                ||repasswordText.getText().toString().isEmpty())){
                    if(!passwordText.getText().toString().equals(repasswordText.getText().toString())){
                        Toast.makeText(SignUpActivity.this,"请确认两次输入的密码相同",Toast.LENGTH_SHORT).show();
                    }else {
                        new SignUpTask().execute();
                    }
                }
                else {
                    Toast.makeText(SignUpActivity.this,"请输入有效的用户名和密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openAlbum()
    {
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,MODIFY_PICTURE);
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
                    Toast.makeText(SignUpActivity.this,"Permission denied.",Toast.LENGTH_SHORT).show();
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
            case MODIFY_PICTURE:
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
                String selection= MediaStore.Images.Media._ID+"="+id;
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
            avatar = BitmapFactory.decodeFile(imagePath);
            userAvatar.setImageBitmap(avatar);
        }
        else {
            Toast.makeText(SignUpActivity.this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    public class SignUpTask extends AsyncTask<Void,Integer,Boolean>{

        private boolean res = false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SignUpActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Connecting");
            progressDialog.setMessage("Loading");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            String strAvatar = Base64.encodeToString(mUtil.bitmap2Bytes(avatar),Base64.DEFAULT);
            Log.d("strAv", "len:"+strAvatar.length());
            try{
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                        /*builder.addFormDataPart("avatar",user.getName()+"_avatar",
                                RequestBody.create(MediaType.parse("image/png"),byteAvatar));*/
                builder.addFormDataPart("avatar",strAvatar);
                builder.addFormDataPart("name",usernameText.getText().toString());
                builder.addFormDataPart("psw",passwordText.getText().toString());//todo 加密
                Request request = new Request.Builder()
                        .url("http://108.61.223.76/sign_up.php")
                        .post(builder.build())
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(0,TimeUnit.SECONDS)
                        .connectTimeout(0, TimeUnit.SECONDS)
                        .writeTimeout(0,TimeUnit.SECONDS)
                        .build();
                Response response = client.newCall(request).execute();
                String resBody = response.body().string();
                Log.d("res", resBody);
                if(new JSONObject(resBody).getString("state").equals("success")){
                    Intent intent = new Intent();
                    User user = new User();
                    user.setAvatar(strAvatar);
                    user.setName(usernameText.getText().toString());
                    intent.putExtra("usr_data",user);
                    setResult(RESULT_OK,intent);
                    res=true;
                }
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
            if(!aBoolean){
                Toast.makeText(SignUpActivity.this,"Fail in connect",Toast.LENGTH_SHORT).show();
            }else if(res){
                finish();
            }
            else {
                Toast.makeText(SignUpActivity.this,"Exist account",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
