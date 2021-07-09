package com.bachngo.socialmediaprj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachngo.socialmediaprj.dto.AppUserResponse;
import com.bachngo.socialmediaprj.dto.ConnectionResponse;
import com.bachngo.socialmediaprj.service.FriendConnectionService;

import lombok.AllArgsConstructor;

/**
 * controller for all friends- related methodd
 * @author Bach
 *
 */
@RestController
@RequestMapping("/api/friends")
@AllArgsConstructor
public class FriendConnectionController {

	private final FriendConnectionService friendConnectionService;
	
	/**
	 * find all friends of the current user
	 * @return
	 */
	@GetMapping("/find-all-friends")
	public ResponseEntity<List<AppUserResponse>> findAllFriends(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(friendConnectionService.findAllFriendsOfUser());
	}
	
	
	/**
	 * find all friends request of the current user
	 * @return
	 */
	@GetMapping("/find-all-request")
	public ResponseEntity<List<AppUserResponse>> findAllRequest(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(friendConnectionService.findAllRequestOfUser());
	}

	/**
	 * find the connection betweeen the current user and user at "requestdeeId"
	 * @param requestdeeId
	 * @return: "NO CONNECTION" if there are no connection between two users"
	 * "FRIEND": if two users are friends and "REQUESTED" if the user have requested friend
	 * "SELF" if requestdeeUser and current user are the same
	 */
	@GetMapping("/{requestdeeId}")
	public ResponseEntity<ConnectionResponse> findConnection(@PathVariable Long requestdeeId){
		return ResponseEntity.status(HttpStatus.OK)
				.body( friendConnectionService.findConnection(requestdeeId));
	}

	/**
	 * send friend request
	 * @param requestdeeId
	 * @return
	 */
	@PostMapping("/sendFriendRequest/{requestdeeId}")
	public ResponseEntity<String> sendFriendRequest(@PathVariable Long requestdeeId){
		friendConnectionService.sendFriendRequest(requestdeeId);
		return ResponseEntity.status(HttpStatus.OK).body("Success");
	}

	@PostMapping("/sendAcceptRequest/{requestderId}")
	public ResponseEntity<String> acceptRequest(@PathVariable Long requestderId){
		friendConnectionService.sendAcceptRequest(requestderId);
		return ResponseEntity.status(HttpStatus.OK).body("Accepted!!");
	}

	@PostMapping("/sendUnfriendRequest/{requestdeeId}")
	public ResponseEntity<String> sendUnfriendRequest(@PathVariable Long requestdeeId){
		friendConnectionService.sendUnfriendRequest(requestdeeId);
		return ResponseEntity.status(HttpStatus.OK).body("Unfriended");
	}

}
