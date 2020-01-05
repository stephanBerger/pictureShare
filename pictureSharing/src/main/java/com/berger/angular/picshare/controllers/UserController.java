package com.berger.angular.picshare.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import com.berger.angular.picshare.entities.User;
import com.berger.angular.picshare.repository.IUser;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
    private IUser userRepository;
    
    @Autowired
    private Logger LOGGER;


	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {LOGGER.info("Requête list USERS");
		return ResponseEntity.ok(this.userRepository.findAll());
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<Serializable> findUserBysId(@PathVariable(name = "idUser") Long idUser) {
		if (idUser == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve user with null ID");
		}
		User user = this.userRepository.getOne(idUser);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/")
	public ResponseEntity<Serializable> createUser(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.badRequest().body("Cannot create user with empty fields");
		}
		User createdUser = this.userRepository.save(user);
		return ResponseEntity.ok(createdUser);
	}

	@PostMapping("/login")
	public ResponseEntity<Serializable> login(@RequestParam(name = "mail") String mail,
			@RequestParam(name = "password") String password) {
		if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)) {
			return ResponseEntity.badRequest()
					.body("Cannot login with empty user mail or password");
		}
		User authenticatedUser = this.userRepository.findByMailAndPassword(mail, password);
		if (authenticatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(authenticatedUser);
	}

}