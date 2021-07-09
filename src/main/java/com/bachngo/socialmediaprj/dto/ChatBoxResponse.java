package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * chat box response when the user want to get the chat boxes that they are currently in
 * @author Bach
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatBoxResponse {
	
	public Long boxId;
	public String nameOfBoxChat;
	
}
