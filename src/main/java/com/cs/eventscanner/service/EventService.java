package com.cs.eventscanner.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cs.eventscanner.modal.Events;
import com.cs.eventscanner.modal.Logs;
import com.cs.eventscanner.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventService {
	
	
	@Autowired
	private EventRepository repository;
	
	@Value("${flagged.event.threshold}")
	int threashold;
	
	
	public Optional<Events> getEventById(String id) {
		
		return repository.findById(id);
	}
	
	public List<Events> getEvents(){
		
		return repository.findAll();
	}
	
	public List<Events> getFlaggedEvents(){
		
		return repository.findByAlertValue(true);
	}
	
	public boolean parseEventsAndSaveToDb(String path) {

		try (Stream<String> lines = (Files.newBufferedReader(Paths.get(path)).lines())) {
			Map<String, List<Logs>> map = new ConcurrentHashMap<>();
			ObjectMapper mapper = new ObjectMapper();

			lines.parallel().forEach((currentLine) -> {

				try {
					Logs logEntry = mapper.readValue(currentLine, Logs.class);
					
					log.debug("received entry: {}", logEntry);

					if (!map.containsKey(logEntry.getId())) 
					{
						List<Logs> list = new LinkedList<>();
						list.add(logEntry);
						map.putIfAbsent(logEntry.getId(), list);
					} else 	{						
						Logs prevLog = map.get(logEntry.getId()).get(0);
						long duration = Math.abs(prevLog.getTimestamp() - logEntry.getTimestamp());
						boolean alert = duration > threashold ? true : false;
						Events event = new Events.Builder(logEntry.getId())
								           .withHost(logEntry.getHost())
								           .withType(logEntry.getType())
								           .withDuration(duration)
								           .withAlert(alert)
								           .build();
						
						log.debug("Saving event: {}", event);
						
						repository.save(event);

					}
				} catch (JsonMappingException e) {
					log.error("Unable to map event: {}" + e.getMessage());
				} catch (JsonProcessingException e) {
					log.error("Unable to parse event:{}" + e.getMessage());
				}
			});
		} catch (IOException e) {
			log.error("Unable to save event:{}" + e.getMessage());
			return false;
		}
		
		return true;

	}
}
