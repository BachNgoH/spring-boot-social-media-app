package com.bachngo.socialmediaprj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sun.istack.NotNull;

/*
 * 	Cofiguration to allow cors access
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("https://social-media-app-17f17.web.app")
		.allowedMethods("*")
		.maxAge(3600L)
		.allowedHeaders("*")
		.exposedHeaders("Authorization")
		.allowCredentials(true);
	}



}
