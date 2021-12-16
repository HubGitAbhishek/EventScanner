package com.cs.eventscanner.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import com.cs.eventscanner.modal.Events;
import com.cs.eventscanner.service.EventService;


@WebMvcTest(value = EventScannerController.class)
public class EventScannerControllerTest {

    @LocalServerPort
    int randomServerPort;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EventService eventService;
	
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
		
		Mockito.when(eventService.getEventById(Mockito.anyString())).thenReturn(Optional.of(event1));

		String expected = "{id:testId,host:testhost,type:testtype,duration:4,alert:false}";

		this.mockMvc.perform(get("/events/testId")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().json(expected));
	}
	
	@Test
	public void getAllEventTest() throws Exception {
		
		List<Events> eventList = new ArrayList();
		eventList.add(event1);
		eventList.add(event2);
		
		Mockito.when(eventService.getEvents()).thenReturn(eventList);

		String expected = "{id:testId,host:testhost,type:testtype,duration:4,alert:false}";

		this.mockMvc.perform(get("/events")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void getAllFlaggedEventTest() throws Exception {
		
		List<Events> eventList = new ArrayList();

		eventList.add(event2);
		
		Mockito.when(eventService.getFlaggedEvents()).thenReturn(eventList);

		String expected = "[{id:testId2,host:testhost2,type:testtype2,duration:5,alert:true}]";

		this.mockMvc.perform(get("/events/flagged")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(expected));
	}
		
	
}
