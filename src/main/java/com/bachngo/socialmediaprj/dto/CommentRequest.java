package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * request a new comment with the current user id, post id and text
 * however, the current user will be used, not the user at requested userId
 * @author Bach
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
	private Long userId;
	private Long postId;
	private String text;
	
}
