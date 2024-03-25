package com.assessment.azolachat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static com.assessment.azolachat.entity.DBConstants.CHAT_SCHEMA;

@Data
@Entity
@Table(name = "chat_room", schema = CHAT_SCHEMA)
public class Chatroom {
    @Id
    @Column(name = "chatroom_id")
    private long chatRoomId;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "chatroom")
    private List<Message> messages;
}
