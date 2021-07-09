package com.bachngo.socialmediaprj.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * chat box object. At this point, a chat box only allows two users.
 * @author Bach
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatBox {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="first_user_id", referencedColumnName = "id")
	private AppUser firstUser;
	
	@ManyToOne
	@JoinColumn(name="sencond_user_id", referencedColumnName = "id")
	private AppUser secondUser;
	
	@OneToMany
	private List<Message> messages;
	
}
