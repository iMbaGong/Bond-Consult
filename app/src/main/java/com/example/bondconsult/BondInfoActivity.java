package com.example.bondconsult;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BondInfoActivity extends AppCompatActivity {
/*******************债券详细信息******************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bond_info);
        //set actionbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //set fab
        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.fab_info);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Intent intent = getIntent();
        String info = intent.getStringExtra("bond_data");
        TextView textView = (TextView)findViewById(R.id.detail_info);
        textView.setText(info);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
