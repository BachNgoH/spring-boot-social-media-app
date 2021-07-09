package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * when the user request the info of an user
 * an instance of this response will be sent
 * @author Bach
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	
}
