package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.UserException;
import com.example.model.User;
import com.example.repo.UserRepo;

@RestController
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User savedUser = userRepo.save(user);

		return new ResponseEntity<User>(savedUser, HttpStatusCode.valueOf(200));

	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {

		Optional<User> user = userRepo.findById(id);

		return new ResponseEntity<User>(user.get(), HttpStatusCode.valueOf(200));

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable int id) {

		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserException();
		}
		userRepo.deleteById(id);

		return new ResponseEntity<User>(user.get(), HttpStatusCode.valueOf(200));

	}

	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUserById(@RequestBody User user) {
		Optional<User> useropt = userRepo.findById(user.getId());
		if (!useropt.isPresent()) {
			throw new UserException();
		}
		
		
		
		return new ResponseEntity<User>(user, HttpStatusCode.valueOf(200));
		
	}

}
