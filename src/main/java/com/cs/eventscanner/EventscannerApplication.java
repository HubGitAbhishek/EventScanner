package com.cs.eventscanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EventscannerApplication {

	public static void main(String[] args) {
		
		log.info("Starting EventscannerApplication" );
		SpringApplication.run(EventscannerApplication.class, args);
	}

}
