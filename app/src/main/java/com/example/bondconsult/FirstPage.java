package com.example.bondconsult;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        Button rankButton=(Button)findViewById(R.id.rank_button);
        Button newsButton=(Button)findViewById(R.id.news_button);
        Button postButton=(Button)findViewById(R.id.post_button);
        Button userButton=(Button)findViewById(R.id.user_button);

        rankButton.setOnClickListener(this);
        newsButton.setOnClickListener(this);
        postButton.setOnClickListener(this);
        userButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.rank_button:{
                Intent intent=new Intent(FirstPage.this,BondRankActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.news_button:{

            }
            break;
            case R.id.post_button:{
                Intent intent=new Intent(FirstPage.this,PostBar.class);
                startActivity(intent);

            }
            break;
            case R.id.user_button:{
            }
            break;
            default:break;
        }
    }
}
