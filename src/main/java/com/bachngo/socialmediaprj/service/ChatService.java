package com.bachngo.socialmediaprj.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bachngo.socialmediaprj.dto.ChatBoxResponse;
import com.bachngo.socialmediaprj.dto.MessageRequest;
import com.bachngo.socialmediaprj.dto.MessageResponse;
import com.bachngo.socialmediaprj.exceptions.AppException;
import com.bachngo.socialmediaprj.models.AppUser;
import com.bachngo.socialmediaprj.models.ChatBox;
import com.bachngo.socialmediaprj.models.Message;
import com.bachngo.socialmediaprj.repository.AppUserRepository;
import com.bachngo.socialmediaprj.repository.ChatBoxRepository;
import com.bachngo.socialmediaprj.repository.MessageRepository;

import ch.qos.logback.classic.Logger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * chat service for chat-related methods.
 * at first I intended to use the web socket, but that seems to be too 
 * complicated for me now (especially with the authentication stuff). so i use
 * only http requests, which required the front-end app to send a request every second.
 * Not the most optimized solution, will change to web socket soon.
 * 
 * @author Bach
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class ChatService {

	private final ChatBoxRepository chatBoxRepository;
	private final MessageRepository messageRepository;
	private final AppUserDetailsService appUserDetailsService;
	private final AppUserRepository appUserRepository;
	
	public void createNewChatBox (Long secondUserId) {
		AppUser secondUser = appUserRepository.findById(secondUserId)
				.orElseThrow(() -> new AppException("No user found at this id: "+ secondUserId));
		AppUser firstUser = appUserDetailsService.getCurrentUser();
		
		ChatBox existedChatBox = findChatBoxByUsers(firstUser, secondUser);

		if(existedChatBox != null) {
			throw new IllegalStateException("chat box already exists");
		}
		ChatBox newChatBox = ChatBox.builder().firstUser(firstUser).secondUser(secondUser)
				.messages(new ArrayList<Message>()).build();
		chatBoxRepository.save(newChatBox);
	}

	/**
	 * find the chat box by users. two find 
	 * methods need to be called to find the right chat box
	 * @param firstUser
	 * @param secondUser
	 * @return
	 */
	public ChatBox findChatBoxByUsers(AppUser firstUser, AppUser secondUser) {
		Optional<ChatBox> chatbox1 = chatBoxRepository.findByFirstUserAndSecondUser(firstUser, secondUser);
		Optional<ChatBox> chatbox2 = chatBoxRepository.findByFirstUserAndSecondUser(secondUser, firstUser);

		if(chatbox1.isPresent()) {
			return chatbox1.get();
		}
		if(chatbox2.isPresent()) {
			return chatbox2.get();
		}
		return null;
	}
	
	public void createNewMessage(MessageRequest messageRequest) {
		ChatBox chatbox = chatBoxRepository.findById( messageRequest.getChatBoxId())
				.orElseThrow(() -> new IllegalStateException("No chatbox found"));
		AppUser appUser = appUserDetailsService.getCurrentUser();
		
		Message message = Message.builder().createdAt(Instant.now())
				.chatBox(chatbox).sender(appUser).text(messageRequest.getText()).build();
		chatbox .getMessages().add(message);
		
		messageRepository.save(message);
		chatBoxRepository.save(chatbox);
	}
	
	public List<MessageResponse> getMessagesOfAChatBox(Long chatBoxId){
		ChatBox chatbox = chatBoxRepository.findById(chatBoxId)
			.orElseThrow(() -> new AppException("No Chat box found at this id"));
		List<Message> messages = chatbox.getMessages();
		log.info(messages.toString());
		List<MessageResponse> responses = new ArrayList<MessageResponse>();
		for(Message message: messages) {
			String senderUsername = message.getSender().getFirstName()
					+" "+ message.getSender().getLastName();
			responses.add(MessageResponse.builder().id(message.getId())
					.senderUsername(senderUsername)
					.duration(message.getCreatedAt().toString())
					.userId(message.getSender().getId())
					.text(message.getText()).build());
		}
		
		return responses;	
	}

	/**
	 * find all the chat box and map to the chat box response dto
	 * @return the list of chat box responses
	 */
	public List<ChatBoxResponse> getAllChatBoxes() {
		AppUser appUser = appUserDetailsService.getCurrentUser();
		List<ChatBox> chatBoxes= chatBoxRepository.findAllByFirstUserOrSecondUser(appUser, appUser);
		List<ChatBoxResponse> responses = new ArrayList<ChatBoxResponse>();
		for(ChatBox box: chatBoxes) {
			AppUser ortherUser = new AppUser();
			if(appUser.getId() == box.getFirstUser().getId()) {
				ortherUser = box.getSecondUser();
			}
			if(appUser.getId() == box.getSecondUser().getId()) {
				ortherUser = box.getFirstUser();
			}
			String boxChatname = ortherUser.getFirstName() +" "+ ortherUser.getLastName();
			responses.add(new ChatBoxResponse(box.getId(),boxChatname) );
		}
		return responses;
	}
	
	
}
