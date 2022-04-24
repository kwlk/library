package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/SimpleLibrarySpring");
		SpringApplication.run(LibraryApplication.class, args);
		System.out.println("ApplicationStarter has started");
	}

}
