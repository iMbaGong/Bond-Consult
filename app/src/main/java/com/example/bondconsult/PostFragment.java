package com.example.bondconsult;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    static public List<Post> mPostList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.post_list_rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initList();
        recyclerView.setAdapter(new PostListAdapter(mPostList));

        return view;
    }

    private void initList(){
        mPostList = new ArrayList<>();
        mPostList.add(new Post(
                BitmapFactory.decodeResource(getResources(),R.drawable.totoro),
                "name",
                "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!\n" +
                        "Hello!Hello!Hello!Hello!Hello!Hello!",
                BitmapFactory.decodeResource(getResources(),R.drawable.avatar),
                "2019-09-01",
                "Hello!"));
    }
}
