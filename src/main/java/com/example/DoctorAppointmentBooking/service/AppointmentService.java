package com.example.DoctorAppointmentBooking.service;

import com.example.DoctorAppointmentBooking.entity.Appointment;
import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void create(Appointment appointment) {
        appointmentRepository.save(appointment);
    }


    public List<Appointment> appointmentList(Integer doctorId) {
        return  appointmentRepository.findByDoctorDoctorId(doctorId);
    }


    public Appointment findByAppointmentId(Integer appointmentId) {
        return  appointmentRepository.getById(appointmentId);
    }


    public List<Appointment> myAppointmentList(Integer patientId) {
        return appointmentRepository.findByPatientPatientId(patientId);
    }
}
