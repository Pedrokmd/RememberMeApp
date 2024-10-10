package com.example.remembermesetup.patient;

import java.util.Date;

public class Medication {
    private String name;
    private String dosage;
    private int frequency;

    public Medication(String name, String dosage, int frequency) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getStringFrequency() {
        return String.valueOf(frequency);
    }
}
