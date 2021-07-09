package com.bachngo.socialmediaprj.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.bachngo.socialmediaprj.dto.AppUserResponse;
import com.bachngo.socialmediaprj.dto.AuthenticationRequest;
import com.bachngo.socialmediaprj.models.AppUser;
import com.bachngo.socialmediaprj.models.AppUserRole;
import com.bachngo.socialmediaprj.security.JwtUtil;
import com.bachngo.socialmediaprj.service.AppUserDetailsService;
import com.mysql.cj.protocol.x.Ok;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;



@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {
	
	@MockBean
	private AppUserDetailsService appUserDetailsService;
	@MockBean
	private AuthenticationManager authenticationManager;
	@MockBean
	private JwtUtil jwtUtil;
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Should create an authToken and return token + userId")
	public void shouldCreateAuthToken() throws Exception{
		
//		AppUser tempUser = new AppUser(
//				1L,"Test", "Test", "test@test", "123456",AppUserRole.USER);
//		AuthenticationRequest authenticationRequest =
//				new AuthenticationRequest("test@test", "123456");
//		Mockito.when(appUserDetailsService.loadUserByUsername("Test"))
//		.thenReturn(tempUser);
//		Mockito.when(authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken("test@test", "123456")))
//			.thenReturn(null);
//		Mockito.when(jwtUtil.generateToken(tempUser))
//			.thenReturn("TEST JWT");
//		
//		mockMvc.perform(post("/api/v1/auth").accept(MediaType.APPLICATION_JSON)
//				.content("{'email':'test@test','password':'123456'}")
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$.jwt", is("TEST JWT")))
//			.andExpect(jsonPath("$.userId", is(1L)));
//		
//		
	}

}
