package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * used when a new post is created
 * @author Bach
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
	private Long userId;
	private String content;

}
