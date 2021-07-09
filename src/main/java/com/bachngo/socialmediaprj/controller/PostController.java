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
import org.springframework.web.bind.annotation.RestController;

import com.bachngo.socialmediaprj.dto.PostRequest;
import com.bachngo.socialmediaprj.dto.PostResponse;
import com.bachngo.socialmediaprj.models.Post;
import com.bachngo.socialmediaprj.service.PostService;

import lombok.AllArgsConstructor;

/**
 * controller for all posts
 * 
 * @author Bach
 *
 */
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

	private final PostService postService;
	
	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts () {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
	}
	
	/**
	 * get the news feed for the current user
	 * the posts posted by the current user's friends will be prioritized,
	 * other posts will come later
	 * @return news-feed posts
	 */
	@GetMapping("/news-feed")
	public ResponseEntity<List<PostResponse>> getNewsFeed(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(postService.getNewsFeedOfAUser());
	}
	
	/**
	 * get all posts of the user at "userId",
	 * used for displaying user's main page
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<List<PostResponse>> getAllPosts (@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsOfAUser(userId));
	}
	
	/**
	 * add new post 
	 * @param postRequest request from the client-side
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> addNewPost(@RequestBody PostRequest postRequest){
		
		postService.save(postRequest);
		return ResponseEntity.status(HttpStatus.OK).body("Post created");
	}
}
