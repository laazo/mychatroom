package com.assessment.azolachat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import static com.assessment.azolachat.entity.DBConstants.CHAT_SCHEMA;

@Data
@Entity
@Table(name = "user", schema = CHAT_SCHEMA)
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    private String userId;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user")
    private List<Message> messages;
}
