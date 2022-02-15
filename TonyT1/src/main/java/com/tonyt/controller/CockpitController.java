
package com.tonyt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tonyt.model.AcarsMessage;
import com.tonyt.model.Flight;
import com.tonyt.service.*;

/**
 * 
 * @author Tony Thongsinthop
 * Main controller class that handles incoming API request from Angular JS script
 *
 */
@RestController
public class CockpitController {

	@Autowired
	private AcarsService acarsService;

	/*
	 * Handle GET request and return a sample flight object consisting of 1..N ACARS message objects.
	 */
	@GetMapping(value = "/flight/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> getFlightData() {
			
			Flight flight = acarsService.getFlight();
			return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
	
	/*
	 * Handle POST request and publish ACARS message to KAFKA and output to file system directory.
	 */
	@PostMapping(value = "/flight/acars/send", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> sendAcarsMessage(@RequestBody AcarsMessage message, HttpServletRequest request) {
			System.out.println("Receive ACARS message from client with uuid =>" + message.getUuid());
			acarsService.sendAcarsMessage(message);
			return new ResponseEntity<String>("SENT", HttpStatus.OK);
	}
}