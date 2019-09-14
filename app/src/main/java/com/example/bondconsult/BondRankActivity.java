package com.example.bondconsult;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONArray;
import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BondRankActivity extends AppCompatActivity {
    /*******************债券排名*******************/

    static public List<Bond> bondList;
    private QMUITipDialog tipDialog;
    private RecyclerView recyclerView;
    private BondAdapter bondAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bond_rank);
        //set actionbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //set fab
        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //load data
        if (bondList == null)
            bondList=DataSupport.findAll(Bond.class);
        Log.d("database", "size:"+bondList.size());
        if(bondList.size()==0){
            tipDialog = new QMUITipDialog.Builder(BondRankActivity.this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord("Loading")
                    .create();
            new DownloadDataTask().execute();
        }else {

            recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            bondAdapter=new BondAdapter(bondList);
            recyclerView.setAdapter(bondAdapter);
        }




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class DownloadDataTask extends AsyncTask<String,Integer,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tipDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                Log.d("Thread", "Thread start");
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://108.61.223.76/download/data.json")
                        .build();
                Response response = client.newCall(request).execute();
                Log.d("Thread", "finish");
                String responseData=response.body().string();
                //Log.d("data", responseData);
                parseJSONWithJSONObject(responseData);
            }catch (Exception e){
                e.printStackTrace();
                Log.d("Error", "something wrong in download");
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            tipDialog.dismiss();
            if(!aBoolean){
                Toast.makeText(BondRankActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }else {
                recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BondRankActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                bondAdapter=new BondAdapter(bondList);
                recyclerView.setAdapter(bondAdapter);
            }
        }
    }


    private void parseJSONWithJSONObject(String data){
        try{
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                Bond bond =Bond.createIntanceByJson(jsonArray.getJSONObject(i));
                bondList.add(bond);
                //bondAdapter.notifyItemInserted(i);
                bond.save();
                Log.d("save", "save "+i+"st data succ");
            }
            Log.d("finish", "parseJSONWithJSONObject: data save finish");
        }catch (Exception e){
            //e.printStackTrace();
            Log.d("Error", "something wrong in json");
        }
    }

}
