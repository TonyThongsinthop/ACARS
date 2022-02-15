package com.tonyt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonyt.model.*;
import com.tonyt.repository.FlightRepository;

/**
 * 
 * @author Tony Thongsinthop
 * Service class that implements ACARS message sending service
 *
 */
@Service(value = "acarsService")
public class AcarsService {

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

	private File sourceFile;

	public Flight getFlight() {
		return flightRepository.getDefaultFlight();
	}

	public void sendAcarsMessage(AcarsMessage message) {
		try {
			sendAcarsMessageToKafka(message);
		} catch (IOException io1) {
			io1.printStackTrace();
		}
		
		sendAcarsMessageToFile(message);
	}

	public void sendAcarsMessageToKafka(AcarsMessage message) throws IOException {
		
		/*
		 * 1. Construct message key.
		 */

		// String messageKeyForKafka = message.getUuid().toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String messageKey = message.getAirlineCode() + message.getFlightNumber() + message.getDepartureAirport()
				+ message.getArrivalAirport() + dateFormat.format(message.getOriginDate());

		/*
		 * 2. Convert ACARS object into JSON string.
		 */
		ObjectMapper mapper = new ObjectMapper();
		String messageString = null;
		try {
			messageString = mapper.writeValueAsString(message);
			System.out.println("JSONstring = " + messageString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		/*
		 * 3. Send message to KAFKA topic then close connection.
		 */
		kafkaProducerService.publishToKafka(messageKey, messageString);
		System.out.println("Publishing completed.");
	}

	public void sendAcarsMessageToFile(AcarsMessage message) {

		String fileName = "acarMessage_" + message.getUuid() + ".json";
		String workingDirectory = System.getProperty("user.home");
		sourceFile = new File(workingDirectory, fileName);

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(sourceFile, message);
			System.out.println("Output file to => " + workingDirectory + "/" + fileName);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}