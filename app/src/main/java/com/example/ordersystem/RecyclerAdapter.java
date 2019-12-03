package com.example.ordersystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Movie> movieList;
    Intent intent;
    private Context context;
    String movie_name,date,info="";
    int img;
    Bundle bag;
    String idReserve ="reserve movie";

    public RecyclerAdapter(ArrayList<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView movie_img;
        private TextView txt_name,txt_date,txt_info;
        private Button btn_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_img = itemView.findViewById(R.id.movieImg);
            txt_name = itemView.findViewById(R.id.movieName);
            txt_date = itemView.findViewById(R.id.movieDate);
            txt_info = itemView.findViewById(R.id.movieInfo);
            btn_more = itemView.findViewById(R.id.btnMore);

        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, final int position) {
        holder.movie_img.setImageResource(movieList.get(position).img);
        holder.txt_name.setText(movieList.get(position).movie_name);
        holder.txt_date.setText(movieList.get(position).date);
        holder.txt_info.setText(movieList.get(position).info);

        //跳到information頁
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(v.getContext(),InformationPage.class);
                bag = new Bundle();
                movie_name = movieList.get(position).getMovie_name();
                date = movieList.get(position).getDate();
                info = movieList.get(position).getInfo();
                img = movieList.get(position).getImg();

                bag.putString("chooseName",String.valueOf(movie_name));
                bag.putString("chooseDate",String.valueOf(date));
                bag.putString("chooseInfo",String.valueOf(info));
                bag.putInt("chooseImg",img);

                intent.putExtras(bag); //傳值
                intent.setClass(v.getContext(),InformationPage.class);

                context.startActivity(intent);
                ((Activity)context).finish();//強制轉型

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
