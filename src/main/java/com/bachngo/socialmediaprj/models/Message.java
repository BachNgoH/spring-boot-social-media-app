package com.bachngo.socialmediaprj.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * message object
 * @author Bach
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message implements Comparable<Message>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private AppUser sender;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="chat_box_id", referencedColumnName = "id")
	private ChatBox chatBox;
	private Instant createdAt;
	private String text;
	@Override
	public int compareTo(Message o) {
		return o.getCreatedAt().compareTo(createdAt);
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender.getId() + ", chatBox=" + chatBox.getId() + ", createdAt=" + createdAt
				+ ", text=" + text + "]";
	}
	
}
