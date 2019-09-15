package com.example.bondconsult;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostFragment extends Fragment {

    static List<Post> mPostList;
    RecyclerView recyclerView;
    PostListAdapter postListAdapter;
    View view;
    //是否可见
    public boolean isVisible = false;
    //是否初始化完成
    public boolean isInit = false;
    //是否已经加载过
    public boolean isLoadOver = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        setParam();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_post,container,false);
        mPostList = new ArrayList<>();
        isInit = true;
        setParam();
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startTask();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }
    public void startTask(){
        new DownloadTask().execute();
    }
    ////download post_info////
    public class DownloadTask extends AsyncTask<Void,Integer,Boolean>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading data");
            progressDialog.setMessage("Loading");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                if(mPostList.size()>0)mPostList.clear();
                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20,TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url("http://108.61.223.76/get_posts.php")
                        .build();
                Response response = client.newCall(request).execute();
                String resBody = response.body().string();
                Log.d("res in postFrag", "length:"+resBody.length());
                ////parse into post_list////
                JSONArray jsonArray = new JSONArray(resBody);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject  jsonObject = jsonArray.getJSONObject(i);
                    mPostList.add(new Post(
                            jsonObject.getString("title"),
                            jsonObject.getString("text"),
                            jsonObject.getString("time"),
                            jsonObject.getString("user"),
                            jsonObject.getInt("thumb"),
                            jsonObject.getInt("comment_num"),
                            jsonObject.getString("pic"),
                            jsonObject.getString("comments")
                            //todo comments
                    ));
                    //Log.d("pic:", jsonObject.getString("pic"));
                    //Log.d("pic", "size:"+mPostList.get(mPostList.size()-1).getPicList().size());
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
                isVisible = false;
                //是否初始化完成
                isInit = false;
                //是否已经加载过
                isLoadOver = false;
            }
            else {
                recyclerView = view.findViewById(R.id.post_list_rev);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                postListAdapter = new PostListAdapter(mPostList);
                recyclerView.setAdapter(postListAdapter);
            }
        }

    }
    private void setParam() {
        if (isInit && !isLoadOver && isVisible) {
            isLoadOver = true;
            startTask();
        }
    }
}
