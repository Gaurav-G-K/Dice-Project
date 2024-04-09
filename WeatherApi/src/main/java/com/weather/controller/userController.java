package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.dto.AuthResponse;
import com.weather.dto.Login;
import com.weather.entity.Users;
import com.weather.service.UserServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class userController {
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/addUser")
	public ResponseEntity<Users> addUser(@RequestBody Users user){
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		return new ResponseEntity<>(userServices.AddUser(user),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser(@RequestBody Login login){
		return new ResponseEntity<>(userServices.loginUser(login),HttpStatus.OK);
	}
}
