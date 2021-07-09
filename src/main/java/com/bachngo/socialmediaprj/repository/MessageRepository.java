package com.bachngo.socialmediaprj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bachngo.socialmediaprj.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

}
