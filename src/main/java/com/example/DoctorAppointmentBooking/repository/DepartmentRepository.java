package com.example.DoctorAppointmentBooking.repository;

import com.example.DoctorAppointmentBooking.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department,Integer> {


    Department getByDepartmentName(String departmentName);
}
