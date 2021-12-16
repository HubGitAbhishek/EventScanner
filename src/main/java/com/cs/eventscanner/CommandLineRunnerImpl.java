package com.cs.eventscanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cs.eventscanner.service.EventService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	private EventService service;

	@Override
	public void run(String... args) throws Exception {


		if (args.length>0) {
			log.info("Starting application with log file: ", args[0]);
			service.parseEventsAndSaveToDb(args[0]);
		} else {

			log.error("Logfile path value is not provided");
		}

	}
	
	

}