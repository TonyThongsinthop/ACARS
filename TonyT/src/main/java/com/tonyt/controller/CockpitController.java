
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

@RestController
public class CockpitController {

	@Autowired
	private AcarsService acarsService;

	@RequestMapping("/welcome")
	public ModelAndView TestOnly() {

		String message = "<br><div style='text-align:center;'>"
				+ "<h3>Hello World</h3></div><br>";
		return new ModelAndView("welcome", "message", acarsService.getFlight().getAirlineCode());
	}

	// ACTION: get default flight - https://springframework.guru/spring-requestmapping-annotation/
	@GetMapping(value = "/flight/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> getFlightData() {
			
			Flight flight = acarsService.getFlight();
			return new ResponseEntity<Flight>(flight, HttpStatus.OK); // HTTP Status Code = 200
	}
	
	@PostMapping(value = "/flight/acars/send", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> sendAcarsMessage(@RequestBody AcarsMessage message, HttpServletRequest request) {
			System.out.println("Receive ACARS message from client =>" + message.getUuid());
			acarsService.sendAcarsMessage(message);
			return new ResponseEntity<String>("SENT", HttpStatus.OK); // HTTP Status Code = 200
	}
}