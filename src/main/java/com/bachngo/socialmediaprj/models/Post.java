package com.bachngo.socialmediaprj.models;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Post objects, contains the creator user, comments and all the reacts
 * could be sorted by "createdAt"
 * @author Bach
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="post")
public class Post implements Comparable<Post>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
	
	
	private Instant createdAt;
	
	@JsonIgnore
	@OneToMany( mappedBy = "post")
	private List<React> reacts;
	
	private String content;

	@Override
	public int compareTo(Post o) {
		return o.getCreatedAt().compareTo(createdAt);
	}
	
	
	
}
