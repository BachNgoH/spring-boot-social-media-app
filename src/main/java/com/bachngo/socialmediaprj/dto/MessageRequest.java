package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * used when user send a message, contains the senderId(but the current user will be used)
 * and chatboxId, text of the message
 * @author Bach
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
	
	public Long senderId;
	public Long chatBoxId;
	public String text;
	
}
