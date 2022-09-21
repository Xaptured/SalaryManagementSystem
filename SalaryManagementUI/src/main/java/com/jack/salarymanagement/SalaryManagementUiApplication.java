package com.jack.salarymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SalaryManagementUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalaryManagementUiApplication.class, args);
	}
}
