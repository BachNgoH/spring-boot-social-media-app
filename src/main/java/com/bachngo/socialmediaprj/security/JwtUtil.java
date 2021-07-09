package com.bachngo.socialmediaprj.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.bachngo.socialmediaprj.models.AppUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwt util for authentication 
 * @author Bach
 *
 */
@Service
public class JwtUtil {
	
	/**
	 * SECRET_KEY to create the token, only a normal string to keep it simple 
	 */
	private String SECRET_KEY = "Something Secret";
	
	/**
	 * extract the email from the jwt token
	 * @param token: the jwt token
	 * @return the extracted email
	 */
	public String extractEmail(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	/**
	 * extract the expiration time of the token
	 * @param token
	 * @return
	 */
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(AppUser userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getEmail());
	}
	
	/**
	 * method used to create the token
	 * @param claims
	 * @param subject
	 * @return
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	/**
	 * used to validate the token.
	 * return true if the email extracted from the token is same as the user email
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, AppUser userDetails) {
		final String email = extractEmail(token);
		return (email.equals(userDetails.getEmail())) && !isTokenExpired(token);
	}
	
}
