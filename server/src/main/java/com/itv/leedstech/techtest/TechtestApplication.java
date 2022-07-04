package com.itv.leedstech.techtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TechtestApplication {

	/***
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TechtestApplication.class, args);
	}

}
