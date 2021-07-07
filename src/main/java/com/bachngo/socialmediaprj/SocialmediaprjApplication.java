package com.bachngo.socialmediaprj;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialmediaprjApplication {
	
	
	public static void main(String[] args) throws HibernateException, SQLException {
		SpringApplication.run(SocialmediaprjApplication.class, args);
	}

}
