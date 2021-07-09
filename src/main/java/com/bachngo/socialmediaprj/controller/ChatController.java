package com.bachngo.socialmediaprj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bachngo.socialmediaprj.dto.ChatBoxResponse;
import com.bachngo.socialmediaprj.dto.MessageRequest;
import com.bachngo.socialmediaprj.dto.MessageResponse;
import com.bachngo.socialmediaprj.service.ChatService;

import lombok.AllArgsConstructor;

/**
 * controller for all chat-related methods
 * @author Bach
 *
 */
@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class ChatController {
	
	private final ChatService chatService;
	
	/**
	 * create new box chat, which have 2 users.
	 * current user and user at "userId"
	 * @param userId :id of the orther user
	 * @return: success message
	 */
	@PostMapping
	public ResponseEntity<String> createNewBoxChat(@RequestParam(name="userId") Long userId){
		chatService.createNewChatBox(userId);
		return ResponseEntity.status(HttpStatus.CREATED).body("Create Box Successfully!!");
	}
	
	@GetMapping
	public ResponseEntity< List<MessageResponse> > getAllMessageOfAChatBox(@RequestParam(name = "chatbox") Long chatBoxId) {
		return ResponseEntity.status(HttpStatus.OK).body( chatService.getMessagesOfAChatBox(chatBoxId) );
	}
	
	/**
	 * create new message
	 * @param messageRequest: new message
	 * @return
	 */
	@PostMapping("/message")
	public ResponseEntity<String> createNewMessage(@RequestBody MessageRequest messageRequest){
		chatService.createNewMessage(messageRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("create new message successful!");
	}
	
	/**
	 * get all chat boxes of the current user
	 * @return all chatboxes
	 */
	@GetMapping("/all-chat")
	public ResponseEntity<List<ChatBoxResponse>> getAllChatBox(){
		return ResponseEntity.status(HttpStatus.OK).body( chatService.getAllChatBoxes() );
	}

	
}
