package com.example.DoctorAppointmentBooking;

import com.example.DoctorAppointmentBooking.repository.PatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PatientRepository.class)
public class DoctorAppointmentBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorAppointmentBookingAppApplication.class, args);
	}

}
