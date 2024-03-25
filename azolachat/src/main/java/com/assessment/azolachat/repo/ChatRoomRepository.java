package com.assessment.azolachat.repo;

import com.assessment.azolachat.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<Chatroom, Long> {

}