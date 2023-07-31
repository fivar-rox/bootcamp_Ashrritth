package com.example.timescale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.timescale.GroceryRepository")
public class TimescaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimescaleApplication.class, args);
	}

}
