package com.bachngo.socialmediaprj.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bachngo.socialmediaprj.dto.RegistrationRequest;
import com.bachngo.socialmediaprj.service.RegistrationService;

import lombok.AllArgsConstructor;

/**
 * used to register a new user
 * @author Bach
 *
 */
@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
	
	private final RegistrationService registrationService;
	
	/**
	 * register method, the request contains the user info
	 * the the user info will be saved to the database
	 * @param request
	 * @return
	 */
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
	/**
	 * when the user have registered, a confirmation token will be created and sent
	 * with the confirmation email to the user. the user then have to click the link with confirmation token 
	 * to confirm the account
	 * 
	 * @param token: the confirmation token
	 * @return
	 */
	@GetMapping(path="confirm")
	public String confirmUser(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}
	
}
