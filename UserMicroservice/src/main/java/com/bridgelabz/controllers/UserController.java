package com.bridgelabz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.models.User;

@RestController
@RequestMapping("/rest/user")
public class UserController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/name/{name}")
	public User getUserByName(@PathVariable String name) {
		return restTemplate.getForObject("http://db-service/rest/userdb/name/" + name, User.class);
	}
	
	@PostMapping("")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		return restTemplate.postForEntity("http://db-service/rest/userdb", user, String.class);
	}
}
