package com.bridgelabz.controllers;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.models.User;
import com.bridgelabz.repositories.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
//@RequestMapping("/rest/userdb")
public class DbController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/name/{name}")
	public User getUserByName(@PathVariable String name) {
		return userRepository.findByName(name);
	}
	
	@GetMapping("/all")
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@PostMapping("")
	public void addUser(@RequestBody User user) {
		userRepository.save(user);
	}
	
	@HystrixCommand(commandKey="test", groupKey="test", fallbackMethod="testFallBack")
	@GetMapping("/hystrixtest")
	public String hystrixTest() {
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("Failed...");
		}
		return "No Exception";
	}
	
	@GetMapping("/port")
	public int getPort(HttpServletRequest request) {
		return request.getServerPort();
	}
	
	@GetMapping("/nohystrixtest")
	public String failedApi() {
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("Failed...");
		}
		return "No Exception";
	}
	
	public String testFallBack(Throwable e) {
		return "Exception occured " + e.getMessage();
	}
}
