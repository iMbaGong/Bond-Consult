package com.example.bondconsult;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CommentPostActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_post);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        commentText=(EditText)findViewById(R.id.comment_text);

        Button exitButton=(Button)findViewById(R.id.exit_button);
        Button sendButton=(Button)findViewById(R.id.send_button);

        exitButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

        //使输入框获得焦点
        commentText.requestFocus();
    }

    @Override
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.exit_button:{
                finish();
            }
            break;
            case R.id.send_button:{
                Intent intent=new Intent(CommentPostActivity.this,PostActivity.class);
                intent.putExtra("comment_text",commentText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
            break;
            default:break;
        }
    }
}
