package com.example.cinemacgp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    private final Context context;
    private final List<Reservation> list;

    public ReservationAdapter(Context context, List<Reservation> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reservation_layout, parent, false);
        return new ReservationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder holder, int position) {
        Reservation res = list.get(position);

        Picasso.Builder pBuilder = new Picasso.Builder(context);
        pBuilder.downloader(new OkHttp3Downloader(context));

        pBuilder.build()
                .load("https://image.tmdb.org/t/p/w500" + res.getMovieImg())
                .error(R.drawable.ic_baseline_error_24)
                .placeholder(R.drawable.access_time_24)
                .resize(300,450)
                .into(holder.movieImg);

        holder.movieTitle.setText(res.getMovieTitle());
        holder.customerName.setText(res.getCustomerName());
        holder.cinema.setText(res.getCinema());
        holder.reservationDate.setText(res.getReservationDate());
        holder.room.setText("("+res.getRoom()+")");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView movieImg;
        private final TextView movieTitle;
        private final TextView customerName;
        private final TextView cinema;
        private final TextView reservationDate;
        private final TextView room;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.movieImage);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            customerName = itemView.findViewById(R.id.customerName);
            cinema = itemView.findViewById(R.id.cinemaLocation);
            reservationDate = itemView.findViewById(R.id.reservationDate);
            room = itemView.findViewById(R.id.cinemaRoom);
        }
    }
}
