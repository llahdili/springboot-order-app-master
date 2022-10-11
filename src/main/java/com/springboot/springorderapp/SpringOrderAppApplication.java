package com.springboot.springorderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // fire up a servlet container and serve up our service
public class SpringOrderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOrderAppApplication.class, args);
	}

}
