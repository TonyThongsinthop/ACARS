package com.tonyt.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AcarsMessage {
	
	private UUID uuid;
	
	private String airlineCode;
	
	private String flightNumber;
	
	private String departureAirport;
	
	private String arrivalAirport;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date originDate;
	
	private String messageDirection;

	private String messageType;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date messageDate;
	
	private String messageTime;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:sss")
	private Date messageDeliveredTimeStamp;
	
	private String messageDeliveredTimeStampUTCISO8601;

	private String recipientCode;
	
	private String messageStatus;
	
	private String messageDescription;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

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

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Date getOriginDate() {
		return originDate;
	}

	public void setOriginDate(Date originDate) {
		this.originDate = originDate;
	}

	public String getMessageDirection() {
		return messageDirection;
	}

	public void setMessageDirection(String messageDirection) {
		this.messageDirection = messageDirection;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public Date getMessageDeliveredTimeStamp() {
		return messageDeliveredTimeStamp;
	}

	public void setMessageDeliveredTimeStamp(Date messageDeliveredTimeStamp) {
		this.messageDeliveredTimeStamp = messageDeliveredTimeStamp;
	}

	public String getMessageDeliveredTimeStampUTCISO8601() {
		return messageDeliveredTimeStampUTCISO8601;
	}

	public void setMessageDeliveredTimeStampUTCISO8601(String messageDeliveredTimeStampUTCISO8601) {
		this.messageDeliveredTimeStampUTCISO8601 = messageDeliveredTimeStampUTCISO8601;
	}

	public String getRecipientCode() {
		return recipientCode;
	}

	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getMessageDescription() {
		return messageDescription;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
}
