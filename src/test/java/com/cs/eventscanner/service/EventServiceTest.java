package com.cs.eventscanner.service;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cs.eventscanner.modal.Events;
import com.cs.eventscanner.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
	
	@Mock
	EventRepository repo;
	
    @InjectMocks
    EventService service;
    
    @Mock
    Events mockEvent;
	
	Events event1 =  new Events.Builder("testId")
	           .withHost("testhost")
	           .withType("testtype")
	           .withDuration(4)
	           .withAlert(false)
	           .build();
	
	Events event2 =  new Events.Builder("testId2")
	           .withHost("testhost2")
	           .withType("testtype2")
	           .withDuration(5)
	           .withAlert(true)
	           .build();
	
	
	@Test
	public void getEventByIdTest() throws Exception {
		
		Mockito.when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(event1));

       Assertions.assertEquals(service.getEventById("testId"), Optional.of(event1));
	}
	
	@Test
	public void getAllEventTest() throws Exception {
		
		List<Events> eventList = new ArrayList();
		eventList.add(event1);
		eventList.add(event2);
		
		Mockito.when(repo.findAll()).thenReturn(eventList);

		 Assertions.assertEquals(service.getEvents(), eventList);
	}
	
	@Test
	public void getAllFlaggedEventTest() throws Exception {
		
		List<Events> eventList = new ArrayList();

		eventList.add(event2);
		
		Mockito.when(repo.findByAlertValue(Mockito.anyBoolean())).thenReturn(eventList);
		Assertions.assertEquals(service.getFlaggedEvents(), eventList);
	}
	
	@Test
	public void parseEventAndSaveToDbTest() throws URISyntaxException
	{
		URL res = getClass().getClassLoader().getResource("logfile.txt");
		File file = Paths.get(res.toURI()).toFile();
		String absolutePath = file.getAbsolutePath();
		Assertions.assertTrue(service.parseEventsAndSaveToDb(absolutePath));
		
	}
	
	@Test
	public void parseEventAndSaveToDbFalseTest()
	{

		Assertions.assertFalse(service.parseEventsAndSaveToDb("invalid"));
		
	}

}
