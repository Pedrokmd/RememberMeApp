package com.example.remembermesetup.patient;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Patient {
    private String name;
    private String dob;
    private String stage;
    private String notes;
    private List<com.example.remembermesetup.patient.Medication> medications;
    private String gender;
    private LocalDate startDate;

    public Patient(String name, String dob, String stage, String notes, String gender) {
        this(name, dob, stage, notes, gender, null);
    }


    public Patient(String name, String dob, String stage, String notes, String gender, List<com.example.remembermesetup.patient.Medication> medications) {
        this.name = name;
        this.dob = dob;
        this.stage = stage;
        this.notes = notes;
        this.medications = medications == null ? new ArrayList<>() : medications;
        this.gender = gender;
        this.startDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getStage() {
        return stage;
    }

    public String getNotes() {
        return notes;
    }

    public List<com.example.remembermesetup.patient.Medication> getMedications() {
        return medications;
    }

    public void addMedication(com.example.remembermesetup.patient.Medication medication) {
        medications.add(medication);
    }

    public String getGender() {
        return this.gender;
    }

    /**
     * Get the medications for a given date
     *
     * @param date The date to get the medications for
     * @return A list of medications for the given date
     */
    public List<com.example.remembermesetup.patient.Medication> getMeds(LocalDate date) {
        List<com.example.remembermesetup.patient.Medication> meds = new ArrayList<>();

        // If the date is before the start date, return an empty list
        if (startDate.isAfter(date)) {
            Log.d("Patient", "Date is before start date");
            return meds;
        }

        // For each medication, check if the date is a multiple of the frequency
        for (com.example.remembermesetup.patient.Medication medication : medications) {
            int daysBetween = (int) (date.toEpochDay() - startDate.toEpochDay());

            Log.d("Patient", "Days between: " + daysBetween + " Frequency: " + medication.getFrequency());
            // If the days between the start date and the current date is a multiple of the frequency
            if (daysBetween % medication.getFrequency() == 0) {
                Log.d("Patient", "Adding medication: " + medication.getName());
                meds.add(medication);
            }
        }

        return meds;
    }
}
