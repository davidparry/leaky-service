package com.davidparry.leaky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeakyMemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeakyMemoryApplication.class, args);
	}

}
