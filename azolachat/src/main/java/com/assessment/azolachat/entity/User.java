package com.assessment.azolachat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import static com.assessment.azolachat.entity.DBConstants.CHAT_SCHEMA;

@Data
@Entity
@Table(name = "chat_user", schema = CHAT_SCHEMA)
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id", unique = true)
    private long userId;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user")
    private List<Message> messages;
    //TODO: Attach roles to user
}
