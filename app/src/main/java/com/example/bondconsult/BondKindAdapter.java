package com.example.bondconsult;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BondKindAdapter extends RecyclerView.Adapter<BondKindAdapter.ViewHolder>{

        private List<String> mKindList;
        private Context context;

        static class ViewHolder extends RecyclerView.ViewHolder{
            CardView cardView;
            TextView kind;

            public ViewHolder(View view)
            {
                super(view);
                cardView=(CardView)view;
                kind=view.findViewById(R.id.kind_name);
            }
        }

        public BondKindAdapter(List<String> list){
            mKindList = list;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(context==null){
            context=viewGroup.getContext();
        }

        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_bond_kind,viewGroup,false);
        final BondKindAdapter.ViewHolder viewHolder = new BondKindAdapter.ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.kind!=null){
                    switch (viewHolder.kind.getText().toString()){
                        case "记账式\n国债":
                            context.startActivity(new Intent(context,BondRankActivity.class));
                    }
                }

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.kind.setText(mKindList.get(i));
    }

    @Override
    public int getItemCount() {
        return mKindList.size();
    }
}
