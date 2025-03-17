package com.springboot.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootJdbcConfigApplication {

	public static void main(String[] args) 
	{
		log.info("Starting Spring Boot JDBC Application...");
		SpringApplication.run(SpringBootJdbcConfigApplication.class, args);
		log.info("Application started...");
	}

}
