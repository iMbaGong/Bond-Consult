package com.example.bondconsult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BondRankAdapter extends RecyclerView.Adapter<BondRankAdapter.ViewHodler> {

    private List<BondRank> myRankList;

    static class ViewHodler extends RecyclerView.ViewHolder {
        View rankView;
        TextView rankNum;
        TextView bondName;
        TextView bondId;
        TextView bondFigure;
        TextView bondEarnRate;

        public ViewHodler(View view) {
            super(view);
            rankView=view;
            rankNum = view.findViewById(R.id.rank_num);
            bondName = view.findViewById(R.id.bond_name);
            bondId = view.findViewById(R.id.bond_id);
            bondFigure = view.findViewById(R.id.bond_figure);
            bondEarnRate = view.findViewById(R.id.bond_eranrate);
        }
    }

    public BondRankAdapter(List<BondRank> rankList) {
        myRankList = rankList;
    }

    @Override
    public BondRankAdapter.ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bond_rank, parent, false);
        final BondRankAdapter.ViewHodler hodler = new BondRankAdapter.ViewHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(BondRankAdapter.ViewHodler holder, final int position) {
        BondRank bondRank = myRankList.get(position);
        holder.rankNum.setText(bondRank.getRankNum());
        holder.bondName.setText(bondRank.getBondName());
        holder.bondId.setText(bondRank.getBondId());
        holder.bondFigure.setText(bondRank.getBondFigure());
        holder.bondEarnRate.setText(bondRank.getBondEarnRate());

        holder.rankView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity currentActivity = (Activity) v.getContext();
                Intent intent = new Intent(currentActivity ,BondActivity.class);
                currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myRankList.size();
    }
}
