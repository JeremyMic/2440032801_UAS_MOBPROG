package com.example.cinemacgp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    TextView movieTitle, movieOverview;
    ImageView movieImage;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movieTitle = findViewById(R.id.movieTitleDetail);
        movieImage = findViewById(R.id.movieImageDetail);
        movieOverview = findViewById(R.id.movieOverview);
        backBtn = findViewById(R.id.backBtn);

        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String image = getIntent().getStringExtra("image");

        movieTitle.setText(title);
        Picasso.Builder pBuilder = new Picasso.Builder(getApplicationContext());
        pBuilder.downloader(new OkHttp3Downloader(getApplicationContext()));

        pBuilder.build()
                .load("https://image.tmdb.org/t/p/w500" + image)
                .error(R.drawable.ic_launcher_background)
                .resize(400,600)
                .into(movieImage);
        movieOverview.setText(overview);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}