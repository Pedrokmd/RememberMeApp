package com.example.remembermesetup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remembermesetup.patient.Patient;
import com.example.remembermesetup.patient.Patients;
import com.google.android.material.textfield.TextInputEditText;

public class CaregiverAboutPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_caregiver_about_patient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onNextButton(View v) {
        // Get the values from the form
        String stage = ((Spinner) findViewById(R.id.stage_spinner)).getSelectedItem().toString();
        String name = getTextFieldValue(R.id.nameEdit);
        String dateOfBirth = getTextFieldValue(R.id.dateOfBirthEdit);
        String notes = getTextFieldValue(R.id.extra_notes_edit);
        String gender = getTextFieldValue(R.id.genderEdit);
        Log.d("CaregiverAboutPatient", "Name: " + name);
        Log.d("CaregiverAboutPatient", "Date of birth: " + dateOfBirth);
        Log.d("CaregiverAboutPatient ", "Notes:" + notes);
        Log.d("CaregiverAboutPatient", "notes: " + notes);
        Log.d("CaregiverAboutPatient", "gender: " + gender);
        Log.d("CaregiverAboutPatient", "stage: " + stage);

        // Makes sure that the data is correct before going in
        if (!validateData(name, gender, dateOfBirth, stage)) {
            return;
        }

        Log.d("CaregiverAboutPatient", "Data is valid");

        // Create a new patient object
        Patient patient = new Patient(name, dateOfBirth, stage, notes, gender);

        // Shops the medication fragment
        showMedsFragment(patient);
    }

    /**
     * Validates the data entered by the user
     *
     * @param name
     * @param gender
     * @param dateOfBirth
     * @param stage
     * @return
     */
    private boolean validateData(String name, String gender, String dateOfBirth, String stage) {

        // Makes sure that there is a name that is entered
        if (!stringValid(name)) {
            Utils.showPopup(this, "Please enter a name");
            return false;
        }

        // Makes sure that there is a gender entered
        if (!stringValid(gender)) {
            Utils.showPopup(this, "Please enter a valid gender");
            return false;
        }

        // Makes sure that there is a date in the date of birth field
        if (!stringValid(dateOfBirth)) {
            Utils.showPopup(this, "Please enter a date of birth");
            return false;
        }

        // Makes sure that the date is actually a proper date
        if (!Utils.isDateValid(dateOfBirth)) {
            Utils.showPopup(this, "Please enter a valid date of birth");
            return false;
        }

        return true;
    }

    /**
     * Checks if a string is valid
     *
     * @param str The string to check
     * @return
     */
    private boolean stringValid(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * Gets the value of a text field
     *
     * @param id
     * @return
     */
    private String getTextFieldValue(int id) {
        // Casts the view to a text field and gets the text
        return ((AppCompatEditText) findViewById(id)).getText().toString();
    }

    /**
     * Shows the medication fragment
     *
     * @param patient
     */
    private void showMedsFragment(Patient patient) {
        // Adds the patient to the list of patients
        String id = Patients.getInstance().addPatient(patient);
        Intent intent = new Intent(this, PatientMedsManager.class);
        intent.putExtra("patientId", id);
        startActivity(intent);
    }
}