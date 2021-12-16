package com.cs.eventscanner.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cs.eventscanner.modal.Events;

@DataJpaTest
public class EventRepositoryTest {
	
	@Autowired
    EventRepository repository;
	
	@Test
	public void testFindByAlertValue() {
		
		Events event2 =  new Events.Builder("testId2")
		           .withHost("testhost2")
		           .withType("testtype2")
		           .withDuration(5)
		           .withAlert(true)
		           .build();
		
		repository.save(event2);		
		Assertions.assertNotNull(repository.findByAlertValue(true));
		
	}

}
