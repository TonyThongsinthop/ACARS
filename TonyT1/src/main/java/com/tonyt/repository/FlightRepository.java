package com.tonyt.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tonyt.model.AcarsMessage;
import com.tonyt.model.Flight;

/**
 * 
 * @author Tony Thongsinthop
 * Manually instantiate data records to simplify the code illustration.
 *
 */
@Repository(value = "flightRepository")
public class FlightRepository {

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
		message1.setMessageType("B3");
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
		message2.setMessageType("A2");
		message2.setRecipientCode("JQ29");
		message2.setMessageDescription("Pre-Departure Clearance Delivered");
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
		message3.setMessageType("B4");
		message3.setRecipientCode("ATC-MEL");
		message3.setMessageDescription("Acknowledge Pre-Departure Clearance");
		message3.setMessageDate(new Date());
		message3.setMessageTime("07:39");

		AcarsMessage message4 = new AcarsMessage();
		message4.setAirlineCode(defaultFlight.getAirlineCode());
		message4.setFlightNumber(defaultFlight.getFlightNumber());
		message4.setOriginDate(defaultFlight.getOriginDate());
		message4.setDepartureAirport(defaultFlight.getDepartureAirport());
		message4.setArrivalAirport(defaultFlight.getArrivalAirport());
		message4.setUuid(UUID.randomUUID());
		message4.setMessageDirection("DOWNLINK");
		message4.setMessageType("QH (OUT)");
		message4.setRecipientCode("ATC-MEL");
		message4.setMessageDescription("Out to run way");
		message4.setMessageDate(new Date());
		message4.setMessageTime("07:45");

		AcarsMessage message5 = new AcarsMessage();
		message5.setAirlineCode(defaultFlight.getAirlineCode());
		message5.setFlightNumber(defaultFlight.getFlightNumber());
		message5.setOriginDate(defaultFlight.getOriginDate());
		message5.setDepartureAirport(defaultFlight.getDepartureAirport());
		message5.setArrivalAirport(defaultFlight.getArrivalAirport());
		message5.setUuid(UUID.randomUUID());
		message5.setMessageDirection("DOWNLINK");
		message5.setMessageType("QB (OFF)");
		message5.setRecipientCode("ATC-MEL");
		message5.setMessageDescription("Off from the ground");
		message5.setMessageDate(new Date());
		message5.setMessageTime("07:51");

		AcarsMessage message6 = new AcarsMessage();
		message6.setAirlineCode(defaultFlight.getAirlineCode());
		message6.setFlightNumber(defaultFlight.getFlightNumber());
		message6.setOriginDate(defaultFlight.getOriginDate());
		message6.setDepartureAirport(defaultFlight.getDepartureAirport());
		message6.setArrivalAirport(defaultFlight.getArrivalAirport());
		message6.setUuid(UUID.randomUUID());
		message6.setMessageDirection("DOWNLINK");
		message6.setMessageType("B1");
		message6.setRecipientCode("TBC");
		message6.setMessageDescription("Request for Oceanic Clearance");
		message6.setMessageDate(new Date());
		message6.setMessageTime("09:45");

		AcarsMessage message7 = new AcarsMessage();
		message7.setAirlineCode(defaultFlight.getAirlineCode());
		message7.setFlightNumber(defaultFlight.getFlightNumber());
		message7.setOriginDate(defaultFlight.getOriginDate());
		message7.setDepartureAirport(defaultFlight.getDepartureAirport());
		message7.setArrivalAirport(defaultFlight.getArrivalAirport());
		message7.setUuid(UUID.randomUUID());
		message7.setMessageDirection("UPLINK");
		message7.setMessageType("A1");
		message7.setRecipientCode("JQ29");
		message7.setMessageDescription("Oceanic Clearance Delivered");
		message7.setMessageDate(new Date());
		message7.setMessageTime("10:15");

		AcarsMessage message8 = new AcarsMessage();
		message8.setAirlineCode(defaultFlight.getAirlineCode());
		message8.setFlightNumber(defaultFlight.getFlightNumber());
		message8.setOriginDate(defaultFlight.getOriginDate());
		message8.setDepartureAirport(defaultFlight.getDepartureAirport());
		message8.setArrivalAirport(defaultFlight.getArrivalAirport());
		message8.setUuid(UUID.randomUUID());
		message8.setMessageDirection("DOWNLINK");
		message8.setMessageType("B2");
		message8.setRecipientCode("TBC");
		message8.setMessageDescription("Oceanic Clearance readback");
		message8.setMessageDate(new Date());
		message8.setMessageTime("10:19");

		AcarsMessage message9 = new AcarsMessage();
		message9.setAirlineCode(defaultFlight.getAirlineCode());
		message9.setFlightNumber(defaultFlight.getFlightNumber());
		message9.setOriginDate(defaultFlight.getOriginDate());
		message9.setDepartureAirport(defaultFlight.getDepartureAirport());
		message9.setArrivalAirport(defaultFlight.getArrivalAirport());
		message9.setUuid(UUID.randomUUID());
		message9.setMessageDirection("DOWNLINK");
		message9.setMessageType("QK (ON)");
		message9.setRecipientCode("ATC-BKK");
		message9.setMessageDescription("Landing at destination");
		message9.setMessageDate(new Date());
		message9.setMessageTime("19:51");

		AcarsMessage message10 = new AcarsMessage();
		message10.setAirlineCode(defaultFlight.getAirlineCode());
		message10.setFlightNumber(defaultFlight.getFlightNumber());
		message10.setOriginDate(defaultFlight.getOriginDate());
		message10.setDepartureAirport(defaultFlight.getDepartureAirport());
		message10.setArrivalAirport(defaultFlight.getArrivalAirport());
		message10.setUuid(UUID.randomUUID());
		message10.setMessageDirection("DOWNLINK");
		message10.setMessageType("QL (IN)");
		message10.setRecipientCode("ATC-BKK");
		message10.setMessageDescription("Come to complete stop");
		message10.setMessageDate(new Date());
		message10.setMessageTime("19:59");

		acarsMessages.add(message1);
		acarsMessages.add(message2);
		acarsMessages.add(message3);
		acarsMessages.add(message4);
		acarsMessages.add(message5);
		acarsMessages.add(message6);
		acarsMessages.add(message7);
		acarsMessages.add(message8);
		acarsMessages.add(message9);
		acarsMessages.add(message10);

		defaultFlight.setAcarsMessages(acarsMessages);

		return defaultFlight;
	}
}
