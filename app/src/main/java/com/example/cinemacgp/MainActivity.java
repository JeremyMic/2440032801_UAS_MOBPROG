package com.example.cinemacgp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cinemacgp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.homeMenu:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.cinemaMenu:
                    replaceFragment(new CinemaFragment());
                    break;
                case R.id.reserveMenu:
                    replaceFragment(new ReservationHistoryFragment());
                    break;
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();
        fragTrans.replace(R.id.fragmentContainer,fragment);
        fragTrans.commit();
    }
}