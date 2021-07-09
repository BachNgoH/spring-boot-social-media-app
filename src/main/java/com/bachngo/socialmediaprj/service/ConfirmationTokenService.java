package com.bachngo.socialmediaprj.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bachngo.socialmediaprj.models.ConfirmationToken;
import com.bachngo.socialmediaprj.repository.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	/**
	 * when a cofiramation token is created, save it to the database
	 * @param confirmationToken
	 */
	public void saveConfirmationToken(ConfirmationToken confirmationToken) {
		confirmationTokenRepository.save(confirmationToken);
	}
	
	public Optional<ConfirmationToken> getToken(String token){
		return confirmationTokenRepository.findByToken(token);
	}
	
	/**
	 * if the token is invalid, throw an error
	 * @param token
	 * @return
	 */
	public boolean setConfirmedAt(String token) {
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
				.orElseThrow(() -> new IllegalStateException("Token not found"));
		confirmationToken.setConfirmedAt(Instant.now());
		confirmationTokenRepository.save(confirmationToken);
		return true;
		
	}
	
}
