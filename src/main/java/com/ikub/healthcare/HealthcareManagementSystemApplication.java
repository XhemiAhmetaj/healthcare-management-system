package com.ikub.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HealthcareManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareManagementSystemApplication.class, args);
	}


}
