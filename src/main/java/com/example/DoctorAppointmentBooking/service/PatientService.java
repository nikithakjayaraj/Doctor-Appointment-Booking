package com.example.DoctorAppointmentBooking.service;

import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }


    public Patient getUserByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }

    public Patient findById(Integer patientId) {
        return patientRepository.getById(patientId);
    }

    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getUserByEmailAndPassword(String email, String password) {
        return patientRepository.findUserByEmailAndPassword(email,password);
    }

    public boolean existsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }
}
