package com.tonyt.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonyt.model.AcarsMessage;
import com.tonyt.model.Flight;

@Repository(value = "flightRepository")
public class FlightRepository {

	private File sourceFile;

	@Value("acars")
	private String topicName;

	public Flight getDefaultFlight() {

		Flight defaultFlight = new Flight();
		defaultFlight.setAirlineCode("JQ");
		defaultFlight.setFlightNumber("29");
		defaultFlight.setOriginDate(new Date());
		defaultFlight.setDepartureAirport("MEL");
		defaultFlight.setArrivalAirport("BKK");
		List<AcarsMessage> acarsMessages = new ArrayList<AcarsMessage>();

		AcarsMessage message1 = new AcarsMessage();

		message1.setAirlineCode(defaultFlight.getAirlineCode());
		message1.setFlightNumber(defaultFlight.getFlightNumber());
		message1.setOriginDate(defaultFlight.getOriginDate());
		message1.setDepartureAirport(defaultFlight.getDepartureAirport());
		message1.setArrivalAirport(defaultFlight.getArrivalAirport());
		message1.setUuid(UUID.randomUUID());
		message1.setMessageDirection("DOWNLINK");
		message1.setMessageType("RQC");
		message1.setRecipientCode("ATC-MEL");
		message1.setMessageDescription("Request for Pre-Departure Clearance");
		message1.setMessageDate(new Date());
		message1.setMessageTime("07:30");

		AcarsMessage message2 = new AcarsMessage();
		message2.setAirlineCode(defaultFlight.getAirlineCode());
		message2.setFlightNumber(defaultFlight.getFlightNumber());
		message2.setOriginDate(defaultFlight.getOriginDate());
		message2.setDepartureAirport(defaultFlight.getDepartureAirport());
		message2.setArrivalAirport(defaultFlight.getArrivalAirport());
		message2.setUuid(UUID.randomUUID());
		message2.setMessageDirection("UPLINK");
		message2.setMessageType("PDC");
		message2.setRecipientCode("ATC-MEL");
		message2.setMessageDescription("Pre-Departure Clearance received");
		message2.setMessageDate(new Date());
		message2.setMessageTime("07:35");

		AcarsMessage message3 = new AcarsMessage();
		message3.setAirlineCode(defaultFlight.getAirlineCode());
		message3.setFlightNumber(defaultFlight.getFlightNumber());
		message3.setOriginDate(defaultFlight.getOriginDate());
		message3.setDepartureAirport(defaultFlight.getDepartureAirport());
		message3.setArrivalAirport(defaultFlight.getArrivalAirport());
		message3.setUuid(UUID.randomUUID());
		message3.setMessageDirection("DOWNLINK");
		message3.setMessageType("OUT");
		message3.setRecipientCode("ATC-MEL");
		message3.setMessageDescription("Out to run way");
		message3.setMessageDate(new Date());
		message3.setMessageTime("07:45");

		AcarsMessage message4 = new AcarsMessage();
		message4.setAirlineCode(defaultFlight.getAirlineCode());
		message4.setFlightNumber(defaultFlight.getFlightNumber());
		message4.setOriginDate(defaultFlight.getOriginDate());
		message4.setDepartureAirport(defaultFlight.getDepartureAirport());
		message4.setArrivalAirport(defaultFlight.getArrivalAirport());
		message4.setUuid(UUID.randomUUID());
		message4.setMessageDirection("DOWNLINK");
		message4.setMessageType("OFF");
		message4.setRecipientCode("ATC-MEL");
		message4.setMessageDescription("Off from the ground");
		message4.setMessageDate(new Date());
		message4.setMessageTime("07:51");

		AcarsMessage message5 = new AcarsMessage();
		message5.setAirlineCode(defaultFlight.getAirlineCode());
		message5.setFlightNumber(defaultFlight.getFlightNumber());
		message5.setOriginDate(defaultFlight.getOriginDate());
		message5.setDepartureAirport(defaultFlight.getDepartureAirport());
		message5.setArrivalAirport(defaultFlight.getArrivalAirport());
		message5.setUuid(UUID.randomUUID());
		message5.setMessageDirection("DOWNLINK");
		message5.setMessageType("ON");
		message5.setRecipientCode("ATC-BKK");
		message5.setMessageDescription("On the ground at destination");
		message5.setMessageDate(new Date());
		message5.setMessageTime("19:51");

		AcarsMessage message6 = new AcarsMessage();
		message6.setAirlineCode(defaultFlight.getAirlineCode());
		message6.setFlightNumber(defaultFlight.getFlightNumber());
		message6.setOriginDate(defaultFlight.getOriginDate());
		message6.setDepartureAirport(defaultFlight.getDepartureAirport());
		message6.setArrivalAirport(defaultFlight.getArrivalAirport());
		message6.setUuid(UUID.randomUUID());
		message6.setMessageDirection("DOWNLINK");
		message6.setMessageType("IN");
		message6.setRecipientCode("ATC-BKK");
		message6.setMessageDescription("Come to complete stop");
		message6.setMessageDate(new Date());
		message6.setMessageTime("19:59");

		acarsMessages.add(message1);
		acarsMessages.add(message2);
		acarsMessages.add(message3);
		acarsMessages.add(message4);
		acarsMessages.add(message5);
		acarsMessages.add(message6);

		defaultFlight.setAcarsMessages(acarsMessages);

		return defaultFlight;
	}

	/*
	 * public String convertDateToISO8601UTC(Date date) { TimeZone tz =
	 * TimeZone.getTimeZone("UTC"); DateFormat df = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'"); df.setTimeZone(tz); return
	 * df.format(date); }
	 */

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

	public void sendAcarsMessageToKafka(AcarsMessage message) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String messageString = null;
		try {
			messageString = mapper.writeValueAsString(message);
			System.out.println("ResultingJSONstring = " + messageString);
		} catch (JsonProcessingException e) {

		}
		
		Properties kafkaProperties = new Properties();
		InputStream fileStream = new FileInputStream(getResourceFile("kafka.properties"));
		kafkaProperties.load(fileStream);

		try (final Producer<String, String> producer = new KafkaProducer<>(kafkaProperties)) {

			RecordMetadata metadata = producer.send(new ProducerRecord<>("acars", message.getUuid().toString(), messageString)).get();
			producer.close();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
}
