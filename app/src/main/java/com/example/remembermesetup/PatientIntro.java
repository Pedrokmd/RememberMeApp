package com.example.remembermesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remembermesetup.patient.Patients;

public class PatientIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onHomeClick(View view) {
        // Go back to the home page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onPatientClick(View view) {
        // Gets the patient ID from the text field
        Patients patients = Patients.getInstance();
        String id = ((EditText) findViewById(R.id.patientIdEdit)).getText().toString();

        // Checks that the patient exists
        if (!patients.doesPatientExist(id)) {
            Utils.showPopup(this, "Patient does not exist");
            return;
        }

        // Shows the patient information of the patient with the given ID
        Intent intent = new Intent(this, PatientView.class);
        intent.putExtra("patientId", id);
        startActivity(intent);
    }
}