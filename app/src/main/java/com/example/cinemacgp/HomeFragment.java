package com.example.cinemacgp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ItemAdapter adapter;
    public static int PAGE = 1;
    public static String API_KEY = "d9a79982910e3bd5218b8cbd314442ee";
    public static String LANGUAGE = "en-US";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);

        MovieServices services = ApiClient.getInstance().create(MovieServices.class);
        Call<MovieData> call = services.getAll(API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                MovieData movies = response.body();
                List<Result> movieList = movies.getResults();

                adapter = new ItemAdapter(getContext(),movieList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Toast.makeText(getContext(), "Error Loading API!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
