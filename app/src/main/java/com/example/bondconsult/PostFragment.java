package com.example.bondconsult;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
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

import com.google.gson.JsonObject;

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

    public List<Post> mPostList;
    RecyclerView recyclerView;
    PostListAdapter postListAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_post,container,false);
        mPostList = new ArrayList<>();
//        new DownloadTask().execute();
        return view;
    }


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
                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(0, TimeUnit.SECONDS)
                        .connectTimeout(0, TimeUnit.SECONDS)
                        .writeTimeout(0,TimeUnit.SECONDS)
                        .build();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                builder.addFormDataPart("recognize","tongji");
                Request request = new Request.Builder()
                        .url("http://108.61.223.76/get_posts.php")
                        .post(builder.build())
                        .build();
                Response response = client.newCall(request).execute();
                String resBody = response.body().string();
                Log.d("res", "length:"+resBody.length());
                JSONArray jsonArray = new JSONArray(resBody);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject  jsonObject = jsonArray.getJSONObject(i);
                    mPostList.add(new Post(
                            jsonObject.getString("title"),
                            jsonObject.getString("text"),
                            jsonObject.getString("time"),
                            jsonObject.getString("user"),
                            jsonObject.getInt("thumb"),
                            jsonObject.getInt("comms")
                    ));

                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            recyclerView = view.findViewById(R.id.post_list_rev);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            postListAdapter = new PostListAdapter(mPostList);
            recyclerView.setAdapter(postListAdapter);
        }


    }
}
