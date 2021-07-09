package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * comment response. will be used when a post is loaded
 * then the comment response will be sent
 * @author Bach
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
	
	public Long postId;
	public Long userId;
	public String username;
	public String text;
}
