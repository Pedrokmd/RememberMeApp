package com.example.remembermesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CaregiverHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_caregiver_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Handle the click on the new patient button
     *
     * @param view
     */
    public void handleNewPatient(View view) {
        goToCreateNewPatientActivity();
    }

    /**
     * Go to the create new patient activity
     */
    private void goToCreateNewPatientActivity() {
        Intent intent = new Intent(this, CaregiverAboutPatient.class);
        startActivity(intent);
    }

    /**
     * Handle the click on the view patients button
     *
     * @param view
     */
    public void handleViewPatients(View view) {
        goToViewPatientsActivity();
    }

    /**
     * Go to the view patients activity
     */
    private void goToViewPatientsActivity() {
        Intent intent = new Intent(this, ViewAllPatients.class);
        startActivity(intent);
    }
}