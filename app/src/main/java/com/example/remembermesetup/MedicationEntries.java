package com.example.remembermesetup;

import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

public class MedicationEntryEdits {
    private AppCompatEditText medicationName;
    private AppCompatEditText dosage;
    private AppCompatEditText frequency;

    public MedicationEntryEdits(AppCompatEditText medicationName, AppCompatEditText dosage, AppCompatEditText frequency) {
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    public String getMedicationName() {
        return medicationName.getText().toString();
    }

    public String getDosage() {
        return dosage.getText().toString();
    }

    public String getFrequency() {
        return frequency.getText().toString();
    }

    /**
     * Checks if the data is valid
     * @return
     */
    public boolean isValid() {
        // Makes sure that there is a medication name that is entered
        if (!Utils.isStringValid(getMedicationName())) {
            Utils.showPopup(medicationName.getContext(), "Please enter a medication name");
            return false;
        }

        // Makes sure that there is a dosage that is entered
        if (!Utils.isStringValid(getDosage())) {
            Utils.showPopup(dosage.getContext(), "Please enter a dosage");
            return false;
        }

        // Makes sure that the dosage is a number
        if (!Utils.isStringValid(getFrequency())) {
            Utils.showPopup(frequency.getContext(), "Please enter a frequency");
            return false;
        }

        // Makes sure that the frequency is a number
        if (!Utils.isNumber(getFrequency())) {
            Utils.showPopup(frequency.getContext(), "Frequency must be a number");
            return false;
        }

        // Makes sure that the frequency is between 1 and 100
        if (!Utils.inRange(Integer.parseInt(getFrequency()), 1, 100)) {
            Utils.showPopup(frequency.getContext(), "Frequency must be between 1 and 100");
            return false;
        }

        return true;
    }
}
