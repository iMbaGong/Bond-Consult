package com.example.bondconsult;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONArray;
import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsListFragment extends Fragment {

    static public List<News> newsList;
    private QMUITipDialog tipDialog;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list,container,false);

        if (newsList == null)
            newsList= DataSupport.findAll(News.class);
        Log.d("database", "size:"+newsList.size());
               if(newsList.size()==0){
            tipDialog = new QMUITipDialog.Builder(getContext())
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord("Loading")
                    .create();
            new NewsListFragment.DownloadDataTask().execute();
        }else {

            recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            newsAdapter=new NewsAdapter(newsList);
            recyclerView.setAdapter(newsAdapter);
        }

        return view;
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
                        .url("http://108.61.223.76/download/news.json")
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
                Toast.makeText(getContext(),"Fail",Toast.LENGTH_SHORT).show();
            }else {
                recyclerView = (RecyclerView)getActivity().findViewById(R.id.recycler_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                newsAdapter=new NewsAdapter(newsList);
                recyclerView.setAdapter(newsAdapter);
            }
        }
    }


    private void parseJSONWithJSONObject(String data){
        try{
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<100&&i<jsonArray.length();i++){
                News news =News.createIntanceByJson(jsonArray.getJSONObject(i));
                newsList.add(news);
                //bondAdapter.notifyItemInserted(i);
                news.save();
                Log.d("save", "save "+i+"st data succ");
            }
            Log.d("finish", "parseJSONWithJSONObject: data save finish");
        }catch (Exception e){
            //e.printStackTrace();
            Log.d("Error", "something wrong in json");
        }
    }
}
