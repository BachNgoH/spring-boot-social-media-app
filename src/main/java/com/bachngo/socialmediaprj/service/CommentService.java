package com.bachngo.socialmediaprj.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bachngo.socialmediaprj.dto.CommentRequest;
import com.bachngo.socialmediaprj.dto.CommentResponse;
import com.bachngo.socialmediaprj.models.AppUser;
import com.bachngo.socialmediaprj.models.Comment;
import com.bachngo.socialmediaprj.models.Post;
import com.bachngo.socialmediaprj.repository.CommentRepository;
import com.bachngo.socialmediaprj.repository.PostRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final AppUserDetailsService appUserDetailsService;
	
	/**
	 * save a new comment
	 * @param commentRequest
	 */
	public void saveComment(CommentRequest commentRequest) {
		
		AppUser user = appUserDetailsService.getCurrentUser();
		
		Post post = postRepository.findById(commentRequest.getPostId())
				.orElseThrow(() -> new IllegalStateException("post not found"));
		
		Comment newComment = Comment.builder().createdAt(Instant.now())
				.post(post).user(user).text(commentRequest.getText()).build();
		commentRepository.save(newComment);
	}
	
	/**
	 * get all comments of the post and then map it to the comment response
	 * @param postId
	 * @return the list of comments response
	 */
	public List<CommentResponse> getAllCommentOfAPost(Long postId){
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalStateException("post not found"));
		List<Comment> comments = post.getComments();
		List<CommentResponse> commentResponses = new ArrayList<CommentResponse>();
		for(Comment comment: comments) {
			commentResponses.add(CommentResponse.builder().postId(postId).text(comment.getText())
					.username(comment.getUser().getFirstName()).userId(comment.getUser().getId()).build());
		}
		return commentResponses;
	}
}
