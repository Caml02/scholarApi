package com.apipgoogle.serpapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.apipgoogle.serpapi"})
public class SerpapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SerpapiApplication.class, args);
	}

}

