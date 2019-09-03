package com.example.bondconsult;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class BondActivity extends AppCompatActivity {
    private RecyclerView infosView;
    private BondAdapter bondAdapter;
    private List<BondInfo> infoList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bond);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        infosView=(RecyclerView)findViewById(R.id.bond_table);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        infosView.setLayoutManager(layoutManager);
        bondAdapter=new BondAdapter(infoList);
        infosView.setAdapter(bondAdapter);

        initInfo();


    }

    private void initInfo() {

        addInfo("债券名称","国债");
        addInfo("债券代码","000000");
        addInfo("债券种类","国债");
        addInfo("债券期限","2年");
        addInfo("现价","100.00元");
        addInfo("面值","");
        addInfo("利息","");
        addInfo("付息方式","");
        addInfo("风险指数","");
        addInfo("到期收益率","");
    }

    public void addInfo(String name,String content)
    {
        BondInfo bondInfo=new BondInfo(name,content);
        infoList.add(bondInfo);
        bondAdapter.notifyItemInserted(infoList.size()-1);
    }
}
