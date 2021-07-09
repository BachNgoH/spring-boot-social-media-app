package com.bachngo.socialmediaprj.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bachngo.socialmediaprj.dto.PostRequest;
import com.bachngo.socialmediaprj.dto.PostResponse;
import com.bachngo.socialmediaprj.dto.ReactCountResponse;
import com.bachngo.socialmediaprj.models.AppUser;
import com.bachngo.socialmediaprj.models.Post;
import com.bachngo.socialmediaprj.models.React;
import com.bachngo.socialmediaprj.models.ReactStatus;
import com.bachngo.socialmediaprj.repository.AppUserRepository;
import com.bachngo.socialmediaprj.repository.PostRepository;

import lombok.AllArgsConstructor;
/**
 * service for posts
 * @author Bach
 *
 */
@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final AppUserRepository appUserRepository;
	private final AppUserDetailsService appUserDetailsService;
	private final ReactService reactService;
	private final FriendConnectionService friendConnectionService;

	/**
	 * get the news-feed of a user. the posts by current user's friends will be 
	 * prioritized and come before the orther posts. the posts are sorted by created date
	 * @return
	 */
	public List<PostResponse> getNewsFeedOfAUser(){
		AppUser currentUser = appUserDetailsService.getCurrentUser();
		List<AppUser> friendsList = friendConnectionService.findAllFriendsOfUserInternal();
		List<Post> posts = new ArrayList<Post>();
		for(AppUser appUser: friendsList) {
			for(Post post: appUser.getPosts()) {
				posts.add(post);
			}
		}
		
		List<Post> sortedList = posts.stream().sorted()
				.collect(Collectors.toList());

		List<Post> remainingPosts = postRepository.findAll().stream().sorted()
				.collect(Collectors.toList());
		for(Post remaining: remainingPosts) {
			if(!sortedList.contains(remaining))
			sortedList.add(remaining);
		}
		
		List<PostResponse> responses = new ArrayList<PostResponse>();
		for(Post post: sortedList) {
			Optional<React> reactOpt = reactService.findByPostAndUser(post, currentUser);

			String userReact = "NULL";
			if(reactOpt.isPresent()) {
				React tempReact = reactOpt.get();
				if(tempReact.getType().equals(ReactStatus.LIKE)){
					userReact = "LIKE";
				}else if(tempReact.getType().equals(ReactStatus.DISLIKE)) {
					userReact = "DISLIKE";
				}
			}

			AppUser postUser = post.getUser();
			ReactCountResponse reactCount = reactService.countReacts(post.getId());
			responses.add(PostResponse.builder()
					.createdAt(post.getCreatedAt().toString())
					.postId(post.getId()).dislikeCount(reactCount.disLikes)
					.likeCount(reactCount.likes).content(post.getContent()).userFirstName(postUser.getFirstName())
					.userLastName(postUser.getLastName()).commentCount(post.getComments().size())
					.userReact(userReact)
					.build());
		}
		return responses;

	}

	/**
	 * get all the posts of a user. used for the user's page
	 * @param userId
	 * @return
	 */
	public List<PostResponse> getAllPostsOfAUser(Long userId) {

		AppUser user = appUserRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user at this id not found!"));
		List<Post> postsUnSorted = postRepository.findAllByUser(user);
		List<Post> posts = postsUnSorted.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

		List<PostResponse> allPostResponses = new ArrayList<>();
		for(Post post: posts) {
			Optional<React> reactOpt = reactService.findByPostAndUser(post, user);

			String userReact = "NULL";
			if(reactOpt.isPresent()) {
				React tempReact = reactOpt.get();
				if(tempReact.getType().equals(ReactStatus.LIKE)){
					userReact = "LIKE";
				}else if(tempReact.getType().equals(ReactStatus.DISLIKE)) {
					userReact = "DISLIKE";
				}
			}

			AppUser postUser = post.getUser();
			ReactCountResponse reactCount = reactService.countReacts(post.getId());
			allPostResponses.add(PostResponse.builder()
					.createdAt(post.getCreatedAt().toString())
					.postId(post.getId()).dislikeCount(reactCount.disLikes)
					.likeCount(reactCount.likes).content(post.getContent()).userFirstName(postUser.getFirstName())
					.userLastName(postUser.getLastName()).commentCount(post.getComments().size())
					.userReact(userReact)
					.build());
		}
		return allPostResponses;

	}

	public List<PostResponse> getAllPosts(){
		List<Post> allPostsUnSort = postRepository.findAll();
		List<Post> allPosts = allPostsUnSort.stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		List<PostResponse> allPostResponses = new ArrayList<>();
		AppUser currentUser = appUserDetailsService.getCurrentUser();

		for(Post post: allPosts) {
			Optional<React> reactOpt = reactService.findByPostAndUser(post, currentUser);

			String userReact = "NULL";
			if(reactOpt.isPresent()) {
				React tempReact = reactOpt.get();
				if(tempReact.getType().equals(ReactStatus.LIKE)){
					userReact = "LIKE";
				}else if(tempReact.getType().equals(ReactStatus.DISLIKE)) {
					userReact = "DISLIKE";
				}
			}

			AppUser postUser = post.getUser();
			ReactCountResponse reactCount = reactService.countReacts(post.getId());
			allPostResponses.add(PostResponse.builder()
					.createdAt(post.getCreatedAt().toString())
					.postId(post.getId()).dislikeCount(reactCount.disLikes)
					.likeCount(reactCount.likes).content(post.getContent()).userFirstName(postUser.getFirstName())
					.userLastName(postUser.getLastName()).commentCount(post.getComments().size())
					.userReact(userReact)
					.build());
		}
		return allPostResponses;
	}

	public void save(PostRequest postRequest) {
		AppUser user = appUserDetailsService.getCurrentUser();
		Post newPost = Post.builder().createdAt(Instant.now()).user(user)
				.content(postRequest.getContent()).build();
		postRepository.save(newPost);
	}




}
