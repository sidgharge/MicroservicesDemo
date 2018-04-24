package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DbMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbMicroserviceApplication.class, args);
	}
}
