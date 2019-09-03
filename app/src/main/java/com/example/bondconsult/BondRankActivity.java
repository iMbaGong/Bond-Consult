package com.example.bondconsult;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BondRankActivity extends AppCompatActivity {
    private RecyclerView rankView;
    private BondRankAdapter bondRankAdapter;
    private List<BondRank> rankList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bond_rank);

        //隐藏工具栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }


        rankView=(RecyclerView)findViewById(R.id.bond_rank);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rankView.setLayoutManager(layoutManager);
        bondRankAdapter=new BondRankAdapter(rankList);
        rankView.setAdapter(bondRankAdapter);

        initRank();
    }

    private void initRank() {

        addRank("1","定向国债","00000000","50%","30%");
        addRank("2","国债 0 1","00000001","60%","40%");
    }

    public void addRank(String rankNum,String bondName,String bondId,String bondFigure,String bondEarnRate)
    {
        BondRank bondRank=new BondRank(rankNum, bondName, bondId, bondFigure, bondEarnRate);
        rankList.add(bondRank);
        bondRankAdapter.notifyItemInserted(rankList.size()-1);
    }
}
