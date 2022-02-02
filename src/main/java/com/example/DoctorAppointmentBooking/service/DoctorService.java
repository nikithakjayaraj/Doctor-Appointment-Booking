package com.example.DoctorAppointmentBooking.service;

import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.findPatientByEmail(email);
    }
}
