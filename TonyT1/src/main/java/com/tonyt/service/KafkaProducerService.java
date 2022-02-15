package com.tonyt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Tony Thongsinthop
 * Service class that implements KAFKA producer.
 *
 */

@Service(value = "kafkaProducerService")
public class KafkaProducerService {
	
	/*
	 * 1. Initiate properties object to be used by KAFKA producer.
	 */
	private Properties kafkaProperties = new Properties();
	private Producer<String, String> producer;
	private final static String topicName = "acars";
	
	/*
	 * 2. Implement constructor to initiate KAFKA producer
	 */
	public KafkaProducerService() {
		
		/*
		 * 2.1 load properties object with content from kafka.properties file
		 */
		InputStream fileStream = null;
		try {
			fileStream = new FileInputStream(getResourceFile("kafka.properties"));
			kafkaProperties.load(fileStream);
			
		} catch (FileNotFoundException fn) {
			fn.printStackTrace();
		}
		  catch (IOException io) {
			  io.printStackTrace();
		  }

		/*
		 * 2.2 set the value of variable "ssl.keystore.location by using the absolute path of .p12 file
		 */
		String keyStoreFileName = kafkaProperties.getProperty("ssl.keystore.location");
		String keyStoreFileAbsoluatePath = getResourceFile(keyStoreFileName).getAbsolutePath();
		kafkaProperties.setProperty("ssl.keystore.location", keyStoreFileAbsoluatePath);

		/*
		 * 2.3 set the value of variable "ssl.keystore.location by using the absolute path of .jks file
		 */
		String trustStoreFileName = kafkaProperties.getProperty("ssl.truststore.location");
		String trustStoreFileAbsoluatePath = getResourceFile(trustStoreFileName).getAbsolutePath();
		kafkaProperties.setProperty("ssl.truststore.location", trustStoreFileAbsoluatePath);
		
		/*
		 * 2.4 Initiate KAFKA producer as singleton instance to be reused across entire application life cycle.
		 * This way we don't have to instantiate a producer for each message publishing (which is an expensive operation)
		 * Important: Implement try and catch block when instantiate the producer so that if producer fails, it won't fail your entire application
		 */
		try {
		producer = new KafkaProducer<>(kafkaProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 3. Implement a generic method to allow KAFKA message publishing
	 */
	public void publishToKafka (String messageKey, String messageString) {
		
		RecordMetadata metadata = null;
		
		try {
			metadata = producer.send(new ProducerRecord<>(topicName, messageKey, messageString))
					.get();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} catch (ExecutionException ee) {
			ee.printStackTrace();
		}
		
		/*
		 * 3.1 Invoke producer.flush() to ensure that all previously sent messages get sent into KAFKA.
		 */
		producer.flush();
		
		System.out.println("Message produced, key: " + messageKey);
		System.out.println("Message produced, offset: " + metadata.offset());
		System.out.println("Message produced, partition : " + metadata.partition());
		System.out.println("Message produced, topic: " + metadata.topic());
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

}
