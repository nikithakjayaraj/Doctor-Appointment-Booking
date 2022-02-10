package com.example.DoctorAppointmentBooking.service;

import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Doctor> doctorList(Integer departmentId) {
        return doctorRepository.findByDepartmentDepartmentId(departmentId);
    }

    public Doctor findByDoctorId(Integer doctorId) {
        return doctorRepository.getById(doctorId);
    }

    public Doctor findById(Integer doctorId) {
        return  doctorRepository.getById(doctorId);
    }

    public Doctor updateDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
    }
}
