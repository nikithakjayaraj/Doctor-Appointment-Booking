package com.example.DoctorAppointmentBooking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer departmentId;

    private String departmentName;


    @OneToMany(mappedBy= "department")
    private List<Doctor> doctor;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department() {
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }
}
