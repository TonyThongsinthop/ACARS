package com.tonyt.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyt.model.*;
import com.tonyt.repository.FlightRepository;

@Service(value = "acarsService")
public class AcarsService {
	
	@Autowired
	private FlightRepository flightRepository;

	// Data load method
	public Flight getFlight() {
		return flightRepository.getDefaultFlight();
	}
	
	// Send ACARS message
	public void sendAcarsMessage(AcarsMessage message) {
		try {
			flightRepository.sendAcarsMessageToKafka(message);
			flightRepository.sendAcarsMessageToFile(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}