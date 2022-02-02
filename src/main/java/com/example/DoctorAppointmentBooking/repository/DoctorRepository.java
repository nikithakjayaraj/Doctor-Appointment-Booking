package com.example.DoctorAppointmentBooking.repository;

import com.example.DoctorAppointmentBooking.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query("Select doctor from Doctor doctor where doctor.email=?1")
    Doctor findPatientByEmail(String email);
}
