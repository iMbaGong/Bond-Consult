package com.example.bondconsult;

import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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
        final EditText usernameText=(EditText)findViewById(R.id.username_text);
        final EditText passwordText=(EditText)findViewById(R.id.password_text);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(usernameText.getText().toString().isEmpty()||passwordText.getText().toString().isEmpty())){
                    User user = new User();
                    user.setName(usernameText.getText().toString());
                    Intent intent=new Intent();
                    intent.putExtra("usr_data",user);
                    setResult(RESULT_OK,intent);
                    finish();
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
}
