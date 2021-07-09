package com.bachngo.socialmediaprj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * return the connection between to users
 * "FRIEND","REQUESTED" or "NO CONNECTION"
 * @author Bach
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionResponse {
	private String connectionStatus;
}
