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
 * Service class that implements KAFKA producer.
 *
 */
@Service(value = "acarsService")
public class AcarsService {

	@Autowired
	private FlightRepository flightRepository;

	private File sourceFile;

	public Flight getFlight() {
		return flightRepository.getDefaultFlight();
	}

	public void sendAcarsMessage(AcarsMessage message) {
		try {
			sendAcarsMessageToKafka(message);
			sendAcarsMessageToFile(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendAcarsMessageToKafka(AcarsMessage message) throws IOException {
		
		/*
		 * 1. Convert java object into JSON string.
		 */
		ObjectMapper mapper = new ObjectMapper();
		String messageString = null;
		try {
			messageString = mapper.writeValueAsString(message);
			System.out.println("ResultingJSONstring = " + messageString);
		} catch (JsonProcessingException e) {

		}

		/*
		 * 2. Initiate property object to be used by KAFKA producer.
		 */
		Properties kafkaProperties = new Properties();
		InputStream fileStream = new FileInputStream(getResourceFile("kafka.properties"));

		kafkaProperties.load(fileStream);

		/*
		 * 2.1. retrieve file name of encrypted .p12 file, obtain its absolute path and pass to variable "ssl.keystore.location".
		 * This will allow the code to fetch the .p12 file.
		 */
		String keyStoreFileName = kafkaProperties.getProperty("ssl.keystore.location");
		String keyStoreFileAbsoluatePath = getResourceFile(keyStoreFileName).getAbsolutePath();
		kafkaProperties.setProperty("ssl.keystore.location", keyStoreFileAbsoluatePath);

		/*
		 * 2.2. retrieve file name of encrypted .jks file, obtain its absolute path and pass to variable "ssl.keystore.location".
		 * This will allow the code to fetch the .jks file.
		 */
		String trustStoreFileName = kafkaProperties.getProperty("ssl.truststore.location");
		String trustStoreFileAbsoluatePath = getResourceFile(trustStoreFileName).getAbsolutePath();
		kafkaProperties.setProperty("ssl.truststore.location", trustStoreFileAbsoluatePath);

		/*
		 * 3. Initiate KAFKA producer.
		 */
		try (final Producer<String, String> producer = new KafkaProducer<>(kafkaProperties)) {

			/*
			 * 4. Construct message key.
			 */

			// String messageKeyForKafka = message.getUuid().toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String messageKeyForKafka = message.getAirlineCode() + message.getFlightNumber()
					+ message.getDepartureAirport() + message.getArrivalAirport()
					+ dateFormat.format(message.getOriginDate());

			/*
			 * 5. Send message to KAFKA topic then close connection.
			 */
			RecordMetadata metadata = producer.send(new ProducerRecord<>("acars", messageKeyForKafka, messageString))
					.get();
			producer.close();

			/*
			 * 6. Read KAFKA message details
			 */
			System.out.println("Message produced, key: " + messageKeyForKafka);
			System.out.println("Message produced, offset: " + metadata.offset());
			System.out.println("Message produced, partition : " + metadata.partition());
			System.out.println("Message produced, topic: " + metadata.topic());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getResourceFile(String fileName) {

		URL res = getClass().getClassLoader().getResource(fileName);
		File file = null;
		try {
			file = Paths.get(res.toURI()).toFile();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file;
	}

	public void sendAcarsMessageToFile(AcarsMessage message) {

		String fileName = "acarMessage_" + message.getUuid() + ".json";
		String workingDirectory = System.getProperty("user.home");
		sourceFile = new File(workingDirectory, fileName);

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(sourceFile, message);
			System.out.println("output file to => " + workingDirectory + "/" + fileName);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}