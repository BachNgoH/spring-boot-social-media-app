package com.bachngo.socialmediaprj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bachngo.socialmediaprj.models.AppUser;
import com.bachngo.socialmediaprj.models.ChatBox;

@Repository
public interface ChatBoxRepository extends JpaRepository<ChatBox, Long>{

	Optional<ChatBox> findByFirstUserAndSecondUser(AppUser firstUser, AppUser secondUser);

	List<ChatBox> findAllByFirstUserOrSecondUser(AppUser firstUser, AppUser secondUser);

}
