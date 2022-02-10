package com.example.DoctorAppointmentBooking.service;

import com.example.DoctorAppointmentBooking.entity.Department;
import com.example.DoctorAppointmentBooking.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department>  departmentList(){
        return departmentRepository.findAll();
    }


    public Department findByDepartmentName(String departmentName) {
        return  departmentRepository.getByDepartmentName(departmentName);
    }
}
