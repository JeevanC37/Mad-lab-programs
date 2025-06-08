package com.example.intentexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText messageInput;
    CalendarView calendarView;
    TextView selectedDateText;
    Button startBtn;
    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.messageInput);
        calendarView = findViewById(R.id.calendarView);
        selectedDateText = findViewById(R.id.selectedDateText);
        startBtn = findViewById(R.id.startBtn);

        // Date selection from CalendarView
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            selectedDateText.setText("Date: " + selectedDate);
        });

        // Start button - send intent with message, date, and current time
        startBtn.setOnClickListener(v -> {
            String message = messageInput.getText().toString();

            // Get current time
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("message", message);
            intent.putExtra("date", selectedDate);
            intent.putExtra("time", currentTime);
            startActivity(intent);
        });
    }
}
// Note: This is the changes that were told to be made in program 2 where you will write a message, select a date from the calendar,
// and then click a button to send the message, date, and current time to another activity. 
//The second activity will display a progress bar while loading and then show the received data.
// Make sure to create the corresponding XML layout files for both activities.