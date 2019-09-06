package com.example.bondconsult;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Bond extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bond,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.bond_kind_rev);
        MainActivity context = (MainActivity)getActivity();
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        List<String> list = new ArrayList<>();
        list.add("记账式\n国债");
        list.add("地方政\n府债券");
        recyclerView.setAdapter(new BondKindAdapter(list));
        return view;
    }
}
