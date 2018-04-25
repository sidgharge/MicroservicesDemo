package com.bridgelabz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	@GetMapping("/message/{message}")
	public void sendMessage(@PathVariable String message) {
		template.send("test-topic", message);
	}
}
