package com.example.bondconsult;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IntRange;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    EditText usernameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        Button loginButton=(Button)findViewById(R.id.login_button);
        Button signupButton=(Button)findViewById(R.id.signup_button);
        usernameText=(EditText)findViewById(R.id.username_text);
        passwordText=(EditText)findViewById(R.id.password_text);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Connecting");
        progressDialog.setMessage("Loading");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(usernameText.getText().toString().isEmpty()||passwordText.getText().toString().isEmpty())){
                    new SignInTask().execute();
                }
                else {
                    Toast.makeText(LoginActivity.this,"请输入有效的用户名和密码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    ////thread:login////
    public class SignInTask extends AsyncTask<Void,Integer,Boolean>{

        private boolean res=true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(0,TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(0,TimeUnit.SECONDS)
                        .build();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                ////add msg////
                builder.addFormDataPart("name",usernameText.getText().toString());
                builder.addFormDataPart("psw",passwordText.getText().toString());//TODO 加密
                Request request = new Request.Builder()
                        .url("http://108.61.223.76/sign_in.php")
                        .post(builder.build())
                        .build();
                Response response = client.newCall(request).execute();
                String resBody = response.body().string();
                Log.d("res", "length:"+resBody.length());
                ////get the user_info and parse it////
                JSONObject jsonObject = new JSONObject(resBody);
                User user = User.createByJson(jsonObject);
                Log.d("res", "id:"+user.getId()+"; avatarLength:"+user.getAvatar().length());
                if(user.getId()==-1){
                    res=false;
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra("usr_data",user);
                    setResult(RESULT_OK,intent);
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
                Toast.makeText(LoginActivity.this,"Fail in connect",Toast.LENGTH_SHORT).show();
            }else if(res){
                finish();
            }
            else {
                Toast.makeText(LoginActivity.this,"Wrong name or password",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
