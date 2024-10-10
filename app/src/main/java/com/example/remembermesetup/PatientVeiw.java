package com.example.remembermesetup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remembermesetup.patient.Medication;
import com.example.remembermesetup.patient.Patient;
import com.example.remembermesetup.patient.Patients;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PatientView extends AppCompatActivity {
    private Patient patient;
    private TextView medicationDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the patient ID from the intent
        Intent intent = getIntent();

        // Get the patient from the Patients singleton
        String patientId = intent.getStringExtra("patientId");
        patient = Patients.getInstance().getPatient(patientId);

        // Show the patient information
        showPatientInfo(patientId);

        // Set up the medication day text view
        medicationDay = findViewById(R.id.medicationDay);

        CalendarView calendarView = findViewById(R.id.medicationCalendar);

        // Set the calendar to the current date
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Log.d("PatientView", "Date changed to " + year + "-" + month + "-" + dayOfMonth);
            handleDateChanged(year, month, dayOfMonth);
        });

        showMedications(LocalDate.now());
    }

    /**
     * Show the patient information on the screen
     *
     * @param id
     */
    private void showPatientInfo(String id) {
        ((TextView) findViewById(R.id.nameLabel)).setText("Name: " + patient.getName());
        ((TextView) findViewById(R.id.stageLabel)).setText("Alzheimer's Stage: " + patient.getStage());
        ((TextView) findViewById(R.id.genderLabel)).setText("Gender: " + patient.getGender());
        ((TextView) findViewById(R.id.dateOfBirthLabel)).setText("Date of Birth: " + patient.getDob());
        ((TextView) findViewById(R.id.extraNotesLabel)).setText(Utils.isStringValid(patient.getNotes()) ? patient.getNotes() : "No notes specified");
        ((TextView) findViewById(R.id.idLabel)).setText("Patient ID: " + id);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void handleDateChanged(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month + 1, day);
        showMedications(date);

    }

    /**
     * Given a date, show the medications for that day on the text view
     *
     * @param date
     */
    private void showMedications(LocalDate date) {
        List<Medication> medications = patient.getMeds(date);
        Log.d("Medications", "Medications for the day: " + medications.size());

        if (medications.isEmpty()) {
            medicationDay.setText("No medications for the day");
            return;
        }

        StringBuilder sb = new StringBuilder("Medications for the day:\n");
        for (Medication medication : medications) {
            sb.append(medication.getName()).append("\n");
            sb.append("    Dosage: ").append(medication.getDosage()).append("\n");
        }

        medicationDay.setText(sb.toString());
        Log.d("PatientView", "Medications for the day: " + sb.toString());

    }


}