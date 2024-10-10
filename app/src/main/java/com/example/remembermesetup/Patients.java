package com.example.remembermesetup.patient;

import com.example.remembermesetup.Utils;

import java.util.Map;
import java.util.HashMap;

public class Patients {
    private static Patients instance;
    private Map<String, com.example.remembermesetup.patient.Patient> patientMap;

    private Patients() {
        patientMap = new HashMap<>();
    }

    public static Patients getInstance() {
        if (instance == null) {
            instance = new Patients();
        }
        return instance;
    }

    public String addPatient(com.example.remembermesetup.patient.Patient patient) {
        String id = Utils.getId();
        patientMap.put(id, patient);
        return id;
    }

    public com.example.remembermesetup.patient.Patient getPatient(String id) {
        return patientMap.get(id);
    }

    public Map<String, com.example.remembermesetup.patient.Patient> getPatients() {
        return patientMap;
    }

    public boolean doesPatientExist(String id) {
        return patientMap.containsKey(id);
    }
}
