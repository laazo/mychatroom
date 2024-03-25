package com.assessment.azolachat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.assessment.azolachat.entity.DBConstants.CHAT_SCHEMA;

@Data
@Entity
@Table(name = "chat_room", schema = CHAT_SCHEMA)
public class Chatroom {
    @Id
    @GeneratedValue
    @Column(name = "chatroom_id")
    private long chatRoomId;

    @Column(name = "chatroom_name")
    private String chatRoomName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "chatroom")
    private List<Message> messages;

    @PrePersist
    private void setDateCreated() {
        if(dateCreated == null) {
            dateCreated = LocalDateTime.now();
        }
    }
}
