package com.example.remembermesetup;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding based on system bars (status bar and navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        // Set initial progress
        int progress = 89; // Example progress value
        progressBar.setProgress(progress);
        progressText.setText(progress + "%");

        Button buttonChecklist = findViewById(R.id.buttonChecklist);
        Button buttonJournal = findViewById(R.id.buttonJournal);
        Button buttonCalendar = findViewById(R.id.buttonCalendar);
        Button buttonMeditation = findViewById(R.id.buttonMeditation);

        buttonChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChecklistActivity.class));
            }
        });

        buttonJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JournalActivity.class));
            }
        });

        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        buttonMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MeditationActivity.class));
            }
        });
    }

    // Method to handle button click and navigate to SecondActivity
    public void onContinueClicked(View view) {
        Intent intent = new Intent(this, SecondActivity2.class);
        startActivity(intent);
    }
}
