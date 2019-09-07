package com.example.bondconsult;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class BondAdapter extends RecyclerView.Adapter<BondAdapter.ViewHolder>  {
/*********************债券排名Adapter***********************/
    private List<Bond> myBondList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        TextView info;
        Bond bond;

        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            name=view.findViewById(R .id.item_name);
            info=view.findViewById(R.id.item_info);
        }
    }
    public BondAdapter(List<Bond> infoList){
        myBondList=infoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
        {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bond,parent,false);

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.bond!=null){
                    String buf="债券代码:"+viewHolder.bond.getCode()+
                            "\n债券简称:"+viewHolder.bond.getNameAbbr()+
                            "\n债券全称:"+viewHolder.bond.getName()+
                            "\n质押券简称"+viewHolder.bond.getPledgeAbbr()+
                            "\n质押券代码:"+viewHolder.bond.getPledgeCode()+
                            "\n期限(年):"+viewHolder.bond.getTerm()+
                            "\n计息方式:"+viewHolder.bond.getIntBear()+
                            "\n付息方式:"+viewHolder.bond.getIntPay()+
                            "\n发行量(亿元):"+viewHolder.bond.getCirculation()+
                            "\n上市日期:"+viewHolder.bond.getListDate()+
                            "\n到期日:"+viewHolder.bond.getDdl();
                    Intent intent = new Intent(context,BondInfoActivity.class);
                    intent.putExtra("bond_data",buf);
                   context.startActivity(intent);
                }
                else {
                    Log.d("Error", "onClick");
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BondAdapter.ViewHolder holder, final int position)
    {
        holder.bond=myBondList.get(position);
        String buf=holder.bond.getNameAbbr();
        holder.name.setText(buf);
        buf=holder.bond.getCode()+"  "+holder.bond.getName();
        holder.info.setText(buf);

    }

    @Override
    public int getItemCount()
    {
        return myBondList.size();
    }
}