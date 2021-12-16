package com.cs.eventscanner.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cs.eventscanner.exception.RecordNotFoundException;
import com.cs.eventscanner.modal.Events;
import com.cs.eventscanner.service.EventService;

@RestController
public class EventScannerController {

	@Autowired
	private EventService service;

	@GetMapping("events/{id}")
	public ResponseEntity<Events> getEventById(@PathVariable String id) {

		Optional<Events> event = service.getEventById(id);

		if (!event.isPresent()) {
			throw new RecordNotFoundException("Invalid event id : " + id);
		}

		return new ResponseEntity<>(event.get(), HttpStatus.OK);
	}

	@GetMapping("events")
	public ResponseEntity<List<Events>> getAllEvents() {

		List<Events> events = service.getEvents();

		if (events.isEmpty()) {
			throw new RecordNotFoundException("No record found ");
		}

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@GetMapping("events/flagged")
	public ResponseEntity<List<Events>> getFlaggedEvents() {

		List<Events> flaggedEvents = service.getFlaggedEvents();

		if (flaggedEvents.isEmpty()) {
			throw new RecordNotFoundException("No record found ");
		}

		return new ResponseEntity<>(flaggedEvents, HttpStatus.OK);
	}

}
