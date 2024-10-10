package com.example.remembermesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remembermesetup.patient.Patient;
import com.example.remembermesetup.patient.Patients;

public class ViewAllPatients extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_all_patients);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layout = findViewById(R.id.patientLayout);

        showPatients();

    }

    private void showPatients() {
        // Get all patients
        // Loop through all patients
        // Create a new view for each patient
        // Add the view to the layout

        Patients patients = Patients.getInstance();
        for (String patientId : patients.getPatients().keySet()) {
            Patient patient = patients.getPatient(patientId);
            LinearLayout patientView = createPatientView(patientId, patient);
            layout.addView(patientView);
        }
    }

    /**
     * Create a view for a patient
     * @param patientId
     * @param patient
     * @return
     */
    private LinearLayout createPatientView(String patientId, Patient patient) {
        LinearLayout patientView = new LinearLayout(this);

        // add gap between patients
        patientView.setPadding(16, 16, 0, 0);


        // Set the orientation of the layout
        patientView.setOrientation(LinearLayout.HORIZONTAL);

        // Create a text view for the patient's name
        TextView nameView = new TextView(this);
        nameView.setText("Name: " + patient.getName());

        // Add the name view to the layout
        patientView.addView(nameView);
        // Grow the name view to fill the available space
        nameView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        Button goToPatientButton = new Button(this);
        goToPatientButton.setText("View");
        goToPatientButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PatientView.class);
            intent.putExtra("patientId", patientId);
            startActivity(intent);
        });

        // Add the button to the layout
        patientView.addView(goToPatientButton);

        return patientView;
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}