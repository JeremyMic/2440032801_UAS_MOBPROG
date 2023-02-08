package com.example.cinemacgp;

import static com.example.cinemacgp.ReservationHistoryFragment.reservationList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class ReserveMovieActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText inputMovie, inputName, inputDate;
    Spinner spinner, spinner2;
    String[] cinema = {"Choose Cinema:","Cinema Alpha","Cinema Beta"};
    String[] room = {"Choose Room:","Room 1", "Room 2", "Room 3"};
    Button submit,chooseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_movie);

        inputMovie = findViewById(R.id.titleInput);
        inputName = findViewById(R.id.nameInput);
        inputDate = findViewById(R.id.dateInput);
        chooseDate = findViewById(R.id.chooseDateBtn);
        submit = findViewById(R.id.submitBtn);
        spinner = findViewById(R.id.cinemaSpinner);
        spinner2 = findViewById(R.id.roomSpinner);

        String title = getIntent().getStringExtra("title");
        inputMovie.setText(title);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,cinema);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,room);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.cinemacgp.DatePicker datePicker;
                datePicker = new com.example.cinemacgp.DatePicker();
                datePicker.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieTitle,customerName,cinema,reservationDate,movieImg,room;

                movieTitle = inputMovie.getText().toString();
                customerName = inputName.getText().toString();
                cinema = spinner.getSelectedItem().toString();
                reservationDate = inputDate.getText().toString();
                movieImg = getIntent().getStringExtra("image");
                room = spinner2.getSelectedItem().toString();

                if(movieTitle.isEmpty()){
                    inputMovie.setError("Movie cannot be empty!");
                    return;
                }

                if(customerName.isEmpty()){
                    inputName.setError("Name cannot be empty!");
                    return;
                }

                if(cinema.equals("Choose Cinema:")){
                    Toast.makeText(ReserveMovieActivity.this, "Must Choose Cinema!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(reservationDate.isEmpty()){
                    inputDate.setError("Date cannot be empty!");
                    return;
                }

                if(room.equals("Choose Room:")){
                    Toast.makeText(ReserveMovieActivity.this, "Must Choose Room!", Toast.LENGTH_SHORT).show();
                    return;
                }

                reservationList.add(new Reservation(movieTitle,customerName,cinema,reservationDate,movieImg,room));
                Toast.makeText(ReserveMovieActivity.this, "Reservation Success!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();
        fragTrans.replace(R.id.fragmentContainer,fragment);
        fragTrans.commit();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String selectedDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        inputDate.setText(selectedDate);
    }
}