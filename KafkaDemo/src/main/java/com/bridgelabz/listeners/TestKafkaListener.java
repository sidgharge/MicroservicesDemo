package com.bridgelabz.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaListener {

	@KafkaListener(topics="test-topic")
	public void listen(ConsumerRecord<String, String> record) {
		System.out.println(record.value());
	}
}
