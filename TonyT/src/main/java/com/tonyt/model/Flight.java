package com.tonyt.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Flight {
	
	private String airlineCode;
	
	private String flightNumber;
	
	private String departureAirport;
	
	private String arrivalAirport;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date originDate;
	
	private List<AcarsMessage> acarsMessages;
	
	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Date getOriginDate() {
		return originDate;
	}

	public void setOriginDate(Date originDate) {
		this.originDate = originDate;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public List<AcarsMessage> getAcarsMessages() {
		return acarsMessages;
	}

	public void setAcarsMessages(List<AcarsMessage> acarsMessages) {
		this.acarsMessages = acarsMessages;
	}
	
    
}