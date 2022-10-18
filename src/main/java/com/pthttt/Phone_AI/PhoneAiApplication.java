package com.pthttt.Phone_AI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.pthttt.Phone_AI")
public class PhoneAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationWebConfiguration.class, args);
	}

}
