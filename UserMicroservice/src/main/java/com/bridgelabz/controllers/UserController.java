package com.bridgelabz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.models.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
//@RequestMapping("/rest/user")
public class UserController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/name/{name}")
	public User getUserByName(@PathVariable String name) {
		return restTemplate.getForObject("http://db-service/" + name, User.class);
	}
	
	@PostMapping("")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		return restTemplate.postForEntity("http://db-service/", user, String.class);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAll() {
		return restTemplate.exchange("http://db-service/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
	}
	
	@GetMapping("/getport")
	public int getPort() {
		return restTemplate.getForObject("http://db-service/port", Integer.class);
	}
	
	@HystrixCommand(groupKey="test", commandKey="test", fallbackMethod="testFallback")
	@GetMapping("/hystrixtest")
	public String hystrixTest() {
		return restTemplate.getForObject("http://db-service/nohystrixtest", String.class);
	}
	
	public String testFallback(Throwable th) {
		return "got ex: " + th.getMessage();
	}
}
