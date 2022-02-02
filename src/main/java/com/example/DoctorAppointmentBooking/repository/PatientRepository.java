package com.example.DoctorAppointmentBooking.repository;

import com.example.DoctorAppointmentBooking.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

    @Query("Select patient from Patient patient where patient.email=?1")
    Patient findPatientByEmail(String email);
}
