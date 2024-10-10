package com.example.remembermesetup;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remembermesetup.patient.Medication;
import com.example.remembermesetup.patient.Patient;
import com.example.remembermesetup.patient.Patients;

import java.util.ArrayList;
import java.util.List;

public class PatientMedsManager extends AppCompatActivity {
    private LinearLayout medicationEntries;
    private List<MedicationEntryEdits> medicationEntryEdits;
    private Patient patient;
    private String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient_meds_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Sets up the medication entries
        medicationEntries = findViewById(R.id.med_layout);
        medicationEntryEdits = new ArrayList<>();

        addMedicationEntry(); // Add the first medication entry

        Intent intent = getIntent();
        patientId = intent.getStringExtra("patientId");
        patient = Patients.getInstance().getPatient(patientId);
    }

    private void addMedicationEntry() {
        medicationEntries.addView(createMedicationEntry());
    }

    public void onAddMedicationClick(View view) {
        addMedicationEntry();
    }

    /**
     * Delete the last medication entry on the screen
     *
     * @param view
     */
    public void deleteLastMedicationEntry(View view) {
        if (medicationEntries.getChildCount() > 1) {
            medicationEntries.removeViewAt(medicationEntries.getChildCount() - 1);
            medicationEntryEdits.remove(medicationEntryEdits.size() - 1);
        }
    }

    /**
     * Create a medication entry for the user to fill out
     *
     * @return the layout for the medication entry
     */
    private LinearLayout createMedicationEntry() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setDividerPadding(16);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        AppCompatTextView title = new AppCompatTextView(this);
        title.setText("Medication Entry");
        layout.addView(title);

        // Create a text view for the medication name
        AppCompatEditText medicationName = new AppCompatEditText(this);
        medicationName.setHint("Medication Name");
        layout.addView(medicationName);


        // Create a text view for the dosage
        AppCompatEditText dosage = new AppCompatEditText(this);
        dosage.setHint("Dosage");
        layout.addView(dosage);


        // Frequency medication is taken
        AppCompatEditText frequency = new AppCompatEditText(this);
        frequency.setHint("Frequency (every x days)");
        frequency.setInputType(2);
        layout.addView(frequency);

        medicationEntryEdits.add(new MedicationEntryEdits(medicationName, dosage, frequency));


        return layout;
    }

    /**
     * When the user is done adding medications
     *
     * @param view the view that was clicked
     */
    public void onCompleteClick(View view) {
        // Validate all medication entries
        for (MedicationEntryEdits entry : medicationEntryEdits) {
            if (!entry.isValid()) {
                return;
            }
        }

        // Save all medication entries
        for (MedicationEntryEdits entry : medicationEntryEdits) {
            Medication medication = new Medication(
                    entry.getMedicationName(),
                    entry.getDosage(),
                    Integer.parseInt(entry.getFrequency())
            );

            patient.addMedication(medication);
        }

        // Go to the patient view
        Intent intent = new Intent(this, PatientView.class);
        intent.putExtra("patientId", patientId);

        startActivity(intent);
    }
}
