package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * each message responsed to the user
 * @author Bach
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
	
	private Long id;
	private Long userId;
	private String senderUsername;
	private String duration;
	private String text;
}
