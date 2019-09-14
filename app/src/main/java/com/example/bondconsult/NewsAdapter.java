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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
private List<News> myNewsList;
private Context context;

static class ViewHolder extends RecyclerView.ViewHolder{
    CardView cardView;
    TextView title;
    TextView time;
    News news;

    public ViewHolder(View view)
    {
        super(view);
        cardView=(CardView)view;
        title=view.findViewById(R .id.news_title);
        time=view.findViewById(R.id.news_time);
    }
}
    public NewsAdapter(List<News> newsList){
        myNewsList=newsList;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent,false);

        final NewsAdapter.ViewHolder viewHolder = new NewsAdapter.ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.news!=null){
                    String link=viewHolder.news.getLink();
                    Intent intent = new Intent(context,NewsActivity.class);
                    intent.putExtra("link",link);
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
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, final int position)
    {
        holder.news=myNewsList.get(position);
        holder.title.setText(holder.news.getTitle());
        holder.time.setText(holder.news.getTime());


    }

    @Override
    public int getItemCount()
    {
        return myNewsList.size();
    }
}
