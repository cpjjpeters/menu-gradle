package com.example.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(MenuApplication.class, args);
//	}
public static void main(String[] args) {
	try {
		SpringApplication.run(MenuApplication.class, args);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
