package com.assessment.azolachat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

import static com.assessment.azolachat.entity.DBConstants.CHAT_SCHEMA;

@Data
@Entity
@Table(name = "message", schema = CHAT_SCHEMA)
public class Message implements Serializable {

    @Id
    @Column(name = "message_id")
    private long messageId;

    @Column(name = "message_content")
    private String messageContent;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "chatroom_id")
    private String chatRoomId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_posted")
    private LocalDate datePosted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chatroom_id", referencedColumnName = "chatroom_id")
    private Chatroom chatroom;

    @PrePersist
    private void setDatePosted() {
        if(datePosted == null) {
            datePosted = LocalDate.now();
        }
    }
}
