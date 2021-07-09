package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * when the user's info is confirmed, an authentication response containing
 * a jwt token and userId will be sent. this info is then stored in the local storage
 * @author Bach
 *
 */
@AllArgsConstructor
@Getter
public class AuthenticationResponse {

	private final String jwt;
	private final Long userId;
}
