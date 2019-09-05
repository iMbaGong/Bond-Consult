package com.example.bondconsult;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class BondAdapter extends RecyclerView.Adapter<BondAdapter.ViewHolder>  {

    private List<Bond> myBondList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        TextView info;

        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            name=view.findViewById(R.id.item_name);
            info=view.findViewById(R.id.item_info);
        }
    }
    public BondAdapter(List<Bond> infoList){
        myBondList=infoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bond_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BondAdapter.ViewHolder holder, final int position)
    {
        Bond bond=myBondList.get(position);
        holder.name.setText(bond.getNameAbbr());
        String buf=bond.getCode()+"  "+bond.getName();
        holder.info.setText(buf);

    }

    @Override
    public int getItemCount()
    {
        return myBondList.size();
    }
}