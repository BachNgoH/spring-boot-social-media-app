package com.bachngo.socialmediaprj.dto;

import com.bachngo.socialmediaprj.models.ReactStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * used to react a post(LIKE or DISLIKE a post)
 * @author Bach
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactRequest {
	
	public Long userId;
	public Long postId;
	public ReactStatus reactStatus;
	
}
