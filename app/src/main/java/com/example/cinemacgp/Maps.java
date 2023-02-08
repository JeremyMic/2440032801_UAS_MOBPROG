package com.example.cinemacgp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.cinemacgp.databinding.ActivityMainBinding;
import com.example.cinemacgp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        double latitude = getIntent().getDoubleExtra("lat",0.0);
        double longitude = getIntent().getDoubleExtra("long",0.0);
        String marker = getIntent().getStringExtra("marker");

        LatLng loc = new LatLng(latitude, longitude);
        this.googleMap.addMarker(new MarkerOptions().position(loc).title(marker));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,18.0f));
    }


}