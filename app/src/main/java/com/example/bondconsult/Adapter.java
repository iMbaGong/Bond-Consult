package com.example.bondconsult;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHodler> {

    private List<myPicture> myPictureList;

    static class ViewHodler extends RecyclerView.ViewHolder{
        ImageView Image;
        Button closeButton;
        public ViewHodler(View view)
        {
            super(view);
            Image=(ImageView)view.findViewById(R.id.m_image);
            closeButton=(Button)view.findViewById(R.id.close_button);
        }
    }
    public Adapter(List<myPicture> pictureList){
        myPictureList=pictureList;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_item,parent,false);
        ViewHodler hodler=new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position)
    {
        myPicture picture=myPictureList.get(position);
//        holder.Image.setImageResource(picture.getImageId());
        holder.Image.setImageBitmap(picture.getImage());
        holder.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPictureList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return myPictureList.size();
    }
}
