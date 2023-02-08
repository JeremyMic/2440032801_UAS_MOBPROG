package com.example.cinemacgp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServices {
    @GET("/3/movie/top_rated")
    Call<MovieData> getAll(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
