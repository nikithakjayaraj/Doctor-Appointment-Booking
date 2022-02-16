package com.example.DoctorAppointmentBooking.repository;

import com.example.DoctorAppointmentBooking.entity.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {


    List<Appointment> findByDoctorDoctorId(Integer doctorId);


    List<Appointment> findByPatientPatientId(Integer patientId);
}
