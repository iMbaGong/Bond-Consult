package com.example.bondconsult;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class BondAdapter extends RecyclerView.Adapter<BondAdapter.ViewHodler>  {

    private List<BondInfo> myInfoList;

    static class ViewHodler extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        public ViewHodler(View view)
        {
            super(view);
            name=view.findViewById(R.id.info_name);
            content=view.findViewById(R.id.info_content);
        }
    }
    public BondAdapter(List<BondInfo> infoList){
        myInfoList=infoList;
    }

    @Override
    public BondAdapter.ViewHodler onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bond_info,parent,false);
        final BondAdapter.ViewHodler hodler=new BondAdapter.ViewHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(BondAdapter.ViewHodler holder, final int position)
    {
        BondInfo bondInfo=myInfoList.get(position);
        holder.name.setText(bondInfo.getName());
        holder.content.setText(bondInfo.getContent());

    }

    @Override
    public int getItemCount()
    {
        return myInfoList.size();
    }
}