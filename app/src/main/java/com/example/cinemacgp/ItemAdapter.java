package com.example.cinemacgp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final Context context;
    private final List<Result> list;

    public ItemAdapter(Context context, List<Result> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Picasso.Builder pBuilder = new Picasso.Builder(context);
        pBuilder.downloader(new OkHttp3Downloader(context));
        Result result = list.get(position);

        pBuilder.build()
                .load("https://image.tmdb.org/t/p/w500" + result.getPosterPath())
                .error(R.drawable.ic_baseline_error_24)
                .placeholder(R.drawable.access_time_24)
                .resize(300,450)
                .into(holder.movieImg);

        holder.movieTitle.setText(result.getTitle());
        holder.movieScore.setText(result.getVoteAverage()+"");

        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahDetail = new Intent(context, MovieDetailActivity.class);
                pindahDetail.putExtra("title",result.getTitle());
                pindahDetail.putExtra("overview",result.getOverview());
                pindahDetail.putExtra("image",result.getPosterPath());
                context.startActivity(pindahDetail);
            }
        });

        holder.reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahReserve = new Intent(context, ReserveMovieActivity.class);
                pindahReserve.putExtra("title",result.getTitle());
                pindahReserve.putExtra("image",result.getPosterPath());
                context.startActivity(pindahReserve);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView movieImg;
        private final TextView movieTitle;
        private final TextView movieScore;
        private final Button detailBtn;
        private final Button reserveBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.movieImage);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieScore = itemView.findViewById(R.id.movieScore);
            detailBtn = itemView.findViewById(R.id.detailBtn);
            reserveBtn = itemView.findViewById(R.id.reserveBtn);
        }
    }
}
