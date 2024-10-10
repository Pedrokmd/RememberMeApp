package com.example.remembermesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Handle the click on the caregiver button
     */
    private void gotoCaregiverHomepage() {
        Intent intent = new Intent(this, CaregiverHomepage.class);
        startActivity(intent);
    }

    /**
     * Handle the click on the caregiver button
     *
     * @param view the view
     */
    public void handleOnClickCaregiver(View view) {
        gotoCaregiverHomepage();
    }

    public void gotoPatientHomepage(View view) {
        Intent intent = new Intent(this, PatientIntro.class);
        startActivity(intent);
    }

}