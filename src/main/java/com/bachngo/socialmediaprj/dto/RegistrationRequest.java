package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * contains the user info for registration
 * @author Bach
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationRequest {

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	

}
